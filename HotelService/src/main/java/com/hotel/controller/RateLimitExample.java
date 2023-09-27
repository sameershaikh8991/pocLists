package com.hotel.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/rate-limit")
public class RateLimitExample {


    @GetMapping
    public String getLimit(){
        return "hello";
    }
}
