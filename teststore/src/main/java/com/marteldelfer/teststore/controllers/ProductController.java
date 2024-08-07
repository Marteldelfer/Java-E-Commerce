package com.marteldelfer.teststore.controllers;

import java.util.List;

import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.marteldelfer.teststore.models.Product;
import com.marteldelfer.teststore.repositories.ProductRepository;

@Controller
public class ProductController {
    
    @Autowired
    private ProductRepository repo;

    @GetMapping("/crud")
    public String showCrudPage(Model model) {
        List<Product> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("products", products);
        return "product-crud.html";
    }
}
