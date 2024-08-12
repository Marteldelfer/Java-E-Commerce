package com.marteldelfer.teststore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marteldelfer.teststore.models.User;
import com.marteldelfer.teststore.repositories.UserRepository;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    UserRepository userRepo;

    @GetMapping({"/",""})
    public String showProfile(Model model) {

        //Get current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepo.findByEmail(email);

        model.addAttribute("user", user);

        return "profile-page.html";
    }

}
