package cl.option.validatephone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtValidationResult {
    private boolean valid;
    private String number;
    private String local_format;
    private String international_format;
    private String country_prefix;
    private String country_code;
    private String country_name;
    private String location;
    private String carrier;
    private String line_type;
}