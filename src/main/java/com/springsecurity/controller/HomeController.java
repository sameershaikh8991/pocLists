package com.springsecurity.controller;


import com.springsecurity.model.LoginRequest;
import com.springsecurity.model.User;
import com.springsecurity.model.UserRegistrationDto;
import com.springsecurity.repo.UserRepository;
import com.springsecurity.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService userDetailsService;




    @GetMapping("/public")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("home");
    }

    @GetMapping("/user")
    public ResponseEntity<String> user(){
        return ResponseEntity.ok("user");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin(){
        return ResponseEntity.ok("admin");
    }


//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }

    @PostMapping("/signup")
    public User saveUser(@RequestBody UserRegistrationDto registrationDto){

        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(encoder.encode(registrationDto.getPassword()));

        userRepository.save(user);

        return user;
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {


        System.out.println("username:{} "+loginRequest.getUsername());
        System.out.println(" password:{}"+loginRequest.getPassword());


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean authenticated = authentication.isAuthenticated();

        System.out.println("authenticated"+authenticated);


        return ResponseEntity.ok()
                .body("login done");
    }



}
