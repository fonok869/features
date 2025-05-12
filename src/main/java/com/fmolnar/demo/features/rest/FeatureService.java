package com.fmolnar.demo.features.rest;

import org.springframework.stereotype.Service;

@Service
public class FeatureService {

    public String getFeatureName() {
        return "Rest Feature Service";
    }
}
