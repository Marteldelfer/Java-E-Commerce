package com.marteldelfer.teststore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marteldelfer.teststore.models.Product;
import com.marteldelfer.teststore.repositories.ProductRepository;
import com.marteldelfer.teststore.repositories.UserRepository;

@Controller
public class HomeController {

    @Autowired
    ProductRepository repo;
    
    @GetMapping("")
    public String showHomePage(Model model) {
        List<Product> products = repo.findAll(Sort.by("id"));
        model.addAttribute("products", products);
        return "index.html";
    }

    @GetMapping("/show")
    public String showProduct(
        Model model,
        @RequestParam int id
        ) {
        Product product = repo.findById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("success", false);
        return "show-product.html";
    }
}
