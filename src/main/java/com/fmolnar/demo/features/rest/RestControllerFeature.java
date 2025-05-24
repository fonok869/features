package com.fmolnar.demo.features.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/free")
public class RestControllerFeature {

    @GetMapping(path = "/test")
    public String getRestDefault() {
        return "Hello World";
    }
}
