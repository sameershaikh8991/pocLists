package com.keyclock.controller;

import com.keyclock.dto.UserDTO;
import com.keyclock.service.KeyCloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @Autowired
    KeyCloakService service;

    @GetMapping
    @PreAuthorize("hasRole('client_user')")
    public String hello() {
        return "Hello USER";
    }

    @GetMapping("/hello-2")
    @PreAuthorize("hasRole('client_admin')")
    public String hello2() {
        return "Hello ADMIN";
    }


    @PostMapping("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(@RequestBody UserDTO userDTO){
        service.addUser(userDTO);
        return "User Added Successfully.";
    }

}
