package com.fmolnar.demo.features.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rs")
public class RestControllerFeatureService {

    private final FeatureService featureService;

    public RestControllerFeatureService(FeatureService featureService) {
        this.featureService = featureService;
    }

    @GetMapping(path = "/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>(featureService.getFeatureName(), HttpStatus.OK);
    }

}
