package com.bagas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bagas.constants.CommonConstants.HOME_ENDPOINT;
import static com.bagas.constants.CommonConstants.MAIN_DOMAIN;
import static com.bagas.constants.CommonConstants.SECURED_ENDPOINT;

@RestController
@RequestMapping(MAIN_DOMAIN)
public class HomeController {

    @GetMapping(HOME_ENDPOINT)
    public String home() {
        return "Hello, Home!";
    }

    @GetMapping(SECURED_ENDPOINT)
    public String secured() {
        return "Hello, Secured!";
    }
}
