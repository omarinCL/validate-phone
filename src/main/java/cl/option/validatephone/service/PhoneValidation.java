package cl.option.validatephone.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.option.validatephone.model.ExtValidationResult;
import cl.option.validatephone.model.ValidationResult;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PhoneValidation {
    @Value("${validation.api.key}")
    private String apiKey;

    @Value("${validation.url}")
    private String url;

    @Value("${validation.api.timeout}")
    private int timeout;

    public ResponseEntity<ValidationResult> validate(String phone) {
        var res = new ValidationResult();
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(timeout);
		factory.setConnectTimeout(timeout);
		
        RestTemplate template = new RestTemplate(new BufferingClientHttpRequestFactory(factory));
        String urlWithParams = url.replace("{0}", apiKey).replace("{1}", phone);
        
        ResponseEntity<ExtValidationResult> re = null;
        try {
            re = template.getForEntity(urlWithParams, ExtValidationResult.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        res.setValue(phone);

        if (re != null && re.getStatusCode() == HttpStatus.OK) {
            res.setValid(re.getBody().isValid());
            res.setFormatedValue(re.getBody().getInternational_format());
            res.setValidated(true);
        } 

        return new ResponseEntity<ValidationResult>(res, HttpStatus.OK);
    }
}