package com.marteldelfer.teststore.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Purchase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private List<Integer> productList;

    private List<Integer> productQuantity;

    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getProductList() {
        return productList;
    }

    public void setProductList(List<Integer> productList) {
        this.productList = productList;
    }

    public List<Integer> getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(List<Integer> productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }   
}