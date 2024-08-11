package com.marteldelfer.teststore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.marteldelfer.teststore.models.Cart;
import com.marteldelfer.teststore.models.Purchase;
import com.marteldelfer.teststore.models.User;
import com.marteldelfer.teststore.repositories.CartRepository;
import com.marteldelfer.teststore.repositories.PurchaseRepository;
import com.marteldelfer.teststore.repositories.UserRepository;

@Controller
public class PurchaseController {
    
    @Autowired
    PurchaseRepository purchaseRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    CartRepository cartRepo;

    @GetMapping("/purchase")
    public String purchase(
        Model model
    ) {
        //Finds current user id and cart
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepo.findByEmail(email);
        int userId = user.getId();
        Cart cart = cartRepo.findById(userId).get();

        //Creating  and saving purchase
        Purchase purchase = new Purchase();
        purchase.setProductList(cart.getIndexList());
        purchase.setProductQuantity(cart.getQuantityList());
        purchase.setUserId(userId);
        purchaseRepo.save(purchase);

        if (!cart.getIndexList().isEmpty()) {
            
            //Clearing cart
            List<Integer> newList = new ArrayList<>();
            cart.setIndexList(newList);
            cart.setQuantityList(newList);
            cartRepo.save(cart);

            //Adds purchases to model
            List<Purchase> purchases = purchaseRepo.findByUserId(userId);
            model.addAttribute("success", true);
            model.addAttribute("purchases", purchases);

        } else {
            model.addAttribute("failed", true);
            return "cart.html";
        }

        
        return "purchases.html";
    }
}
