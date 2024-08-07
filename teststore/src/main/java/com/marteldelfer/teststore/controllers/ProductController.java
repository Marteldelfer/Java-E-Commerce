package com.marteldelfer.teststore.controllers;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.marteldelfer.teststore.models.Product;
import com.marteldelfer.teststore.models.ProductDto;
import com.marteldelfer.teststore.repositories.ProductRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/crud")
public class ProductController {
    
    @Autowired
    private ProductRepository repo;

    @GetMapping("")
    public String showCrudPage(Model model) {
        List<Product> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("products", products);
        return "product-crud.html";
    }

    @GetMapping("/create-product")
    public String showCreateProductPage(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        model.addAttribute("success", false);
        return "create-product.html";
    }

    @PostMapping("/create-product")
    public String postCreateProduct(
        Model model,
        @Valid @ModelAttribute ProductDto productDto,
        BindingResult result
    ) {
        
        if (productDto.getImageFile().isEmpty()) {
            result.addError(new FieldError(
                "productDto",
                "imageFile",
                "Image is required"));
        }        

        if (result.hasErrors()) {
            return "create-product.html";
        }

        //save image
        MultipartFile image = productDto.getImageFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + image.getOriginalFilename();

        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                    StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (Exception e) {
            System.out.println("Exeption" + e.getMessage());
        }

        Product newProduct = new Product();
        
        newProduct.setName(productDto.getName());
        newProduct.setBrand(productDto.getBrand());
        newProduct.setCategory(productDto.getCategory());
        newProduct.setDescription(productDto.getDescription());
        newProduct.setPrice(productDto.getPrice());
        newProduct.setQuantity(productDto.getQuantity());
        newProduct.setCreatedAt(createdAt);
        newProduct.setImageName(storageFileName);

        repo.save(newProduct);
        model.addAttribute("success", true);

        return "create-product.html";
    }
    
}
