package com.marteldelfer.teststore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marteldelfer.teststore.models.Cart;
import com.marteldelfer.teststore.models.Product;
import com.marteldelfer.teststore.models.User;
import com.marteldelfer.teststore.repositories.CartRepository;
import com.marteldelfer.teststore.repositories.ProductRepository;
import com.marteldelfer.teststore.repositories.UserRepository;

@Controller
public class CartController {

    @Autowired
    ProductRepository productRepo;

    @Autowired
    CartRepository cartRepo;

    @Autowired
    UserRepository userRepo;
    
    @GetMapping("/add-to-cart")
    public String addToCart(
        Model model,
        @RequestParam int id
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            String email = authentication.getName();
            User user = userRepo.findByEmail(email);
            int userId = user.getId();
            Cart cart = cartRepo.findById(userId).get();

            if (!cart.getIndexList().contains(id)) {
                cart.getIndexList().add(id);
                cart.getQuantityList().add(1);
                cartRepo.save(cart);
            } else {
                int quantityIndex = cart.getIndexList().indexOf(id);
                cart.getQuantityList().set(quantityIndex,
                (cart.getQuantityList().get(quantityIndex) + 1));

                cartRepo.save(cart);
            }

            List<Product> products = new ArrayList<Product>();
            List<Integer> indexList = cart.getIndexList();
            List<Integer> quantityList = cart.getQuantityList();

            indexList.forEach((index) -> products.add(productRepo.findById(index).get()));
            model.addAttribute("products", products);
            model.addAttribute("quantityList", quantityList);

            return "cart.html";

        } else {
            return "/login";
        } 
    }
    @GetMapping("/cart")
    public String cart() {
        return "cart.html";
    }
}