package com.bagas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Security {

    public static void main(String[] args) {
        SpringApplication.run(Security.class, args);;
    }
}
