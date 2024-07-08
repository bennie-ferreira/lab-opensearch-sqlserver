package com.opensearch.opensearch_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Healthcheck {

    @GetMapping("healthcheck")
    public String healthCheck() {
        return "OK";
    }
}


