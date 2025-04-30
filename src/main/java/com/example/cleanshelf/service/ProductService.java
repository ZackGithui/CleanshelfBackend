package com.example.cleanshelf.service;


import com.example.cleanshelf.model.Products;
import com.example.cleanshelf.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//This service class is used to encapsulate the business logic and interact with the repository
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }


    public List<Products> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }


    public Optional<Products> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Products createProduct(Products products) {
        return productRepository.save(products);
    }

    public void deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with id" + id);
        }
    }
}
