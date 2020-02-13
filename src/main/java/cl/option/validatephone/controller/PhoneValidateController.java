package cl.option.validatephone.controller;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.option.validatephone.model.ValidationResult;
import cl.option.validatephone.service.PhoneValidation;

@RestController
@RequestMapping("/api/v1/phone")
@Validated
@CrossOrigin(origins = "*", methods= {RequestMethod.GET}) 
public class PhoneValidateController {
    @Autowired
    private PhoneValidation service;

    @GetMapping("/validate")
    public ResponseEntity<ValidationResult> getValidation(
        @RequestParam(value = "phone") @NotEmpty String phone
    ) {
        return service.validate(phone);
    }
}