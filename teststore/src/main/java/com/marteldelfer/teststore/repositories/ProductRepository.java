package com.marteldelfer.teststore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marteldelfer.teststore.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    
}
