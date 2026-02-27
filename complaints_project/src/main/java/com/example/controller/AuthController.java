package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.repository.UserRepository;
import com.example.model.user;
import java.util.Optional;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/login")
    public Object login(@RequestBody user requestUser) {

        String email = requestUser.getEmail();

        if (!email.endsWith("@klh.edu.in")) {
            return "Enter correct domain mail";
        }

        Optional<user> existingUser = userRepo.findByEmail(email);

        if (existingUser.isPresent()) {
            return existingUser.get();
        } else {
            user newUser = new user();
            newUser.setEmail(email);
            newUser.setRole("USER");
            return userRepo.save(newUser);
        }
    }
}