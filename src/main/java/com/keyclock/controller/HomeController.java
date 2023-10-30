package com.keyclock.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {


    @GetMapping("/home")
    @PreAuthorize("hasRole('client_user')")
    public String home(){
        return "hello sameer";
    }

    @GetMapping("/home1")
    @PreAuthorize("hasRole('client_admin')")
    public String home1(){
        return "hello admin";
    }

}
