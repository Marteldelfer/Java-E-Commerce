package com.marteldelfer.teststore.models;

import java.util.Date;

import jakarta.persistence.*;

@Table
@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String category;
    private String brand;
    private double price;
    private int quantity;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String imageName;
    private Date createdAt;

}
