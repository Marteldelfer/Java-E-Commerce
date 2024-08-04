package com.marteldelfer.teststore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.marteldelfer.teststore.repositories.UserRepository;

@Controller
public class CreateAccountController {
    
    @Autowired
    private UserRepository repo;

    @GetMapping("/create-account")
    public String showCreateAccountPage() {
        return "create-account.html";
    }
}
