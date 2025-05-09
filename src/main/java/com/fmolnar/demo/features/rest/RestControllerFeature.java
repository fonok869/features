package com.fmolnar.demo.features.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerFeature {

    @GetMapping(path = "/")
    public String getRestDefault() {
        return "Hello World";
    }
}
