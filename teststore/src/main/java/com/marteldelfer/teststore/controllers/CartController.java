package com.marteldelfer.teststore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.marteldelfer.teststore.models.Cart;
import com.marteldelfer.teststore.repositories.CartRepository;
import com.marteldelfer.teststore.repositories.ProductRepository;

@Controller
public class CartController {

    @Autowired
    ProductRepository repo;

    @Autowired
    CartRepository cartRepo;
    
    @GetMapping("/add-to-cart")
    public String addToCart() {
        return ":/redirect/show";
    }
}
