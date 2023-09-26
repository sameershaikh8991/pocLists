package com.auth.controller;


import com.auth.model.User;
import com.auth.payload.UserRequest;
import com.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {


    @Autowired
     UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/add")
    public String addUser(@RequestBody User user){
        userService.saveUser(user);
        return "user added";
    }

    @PostMapping("/generate-token")
    public String generateToken(@RequestBody UserRequest request){

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if(authenticate.isAuthenticated()){
            return  userService.generateJwtToken(request.getUsername());
        }
        return "invalid user";

    }

    @GetMapping("/validate-token")
    public String validatedToken(@RequestParam("token") String token){
          userService.validateToken(token);

          return "token valid";
    }
}
