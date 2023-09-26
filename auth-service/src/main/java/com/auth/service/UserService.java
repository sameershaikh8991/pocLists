package com.auth.service;


import com.auth.model.User;
import com.auth.repo.UserrRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserrRepo userrRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private  JwtService  service;


    public String saveUser(User user){

        user.setPassword( passwordEncoder.encode(user.getPassword()));
        User save = userrRepo.save(user);

        return "user saved";
    }

    public String generateJwtToken(String username){
        String token = service.generateToken(username);

        return token;
    }


    public void validateToken(String token){
        service.validateToken(token);
    }
}
