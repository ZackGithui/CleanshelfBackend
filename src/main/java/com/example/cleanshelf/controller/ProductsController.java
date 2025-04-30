package com.example.cleanshelf.controller;


import com.example.cleanshelf.model.Admin;
import com.example.cleanshelf.model.Products;
import com.example.cleanshelf.security.JwtUtil;
import com.example.cleanshelf.service.AdminService;
import com.example.cleanshelf.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductsController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ProductService productService;

    @GetMapping("products")
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("products/{category}")
    public ResponseEntity<List<Products>> getProductsByCategory(@PathVariable String category) {
        List<Products> products = productService.getProductsByCategory(category);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("product")
    public Products createProduct(@RequestBody Products products) {
        return productService.createProduct(products);
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody Admin admin) {
        Admin saved = adminService.register(admin);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Admin loginReq) {
        return adminService.authenticate(loginReq.getEmail(), loginReq.getPassword())
                .map(admin -> ResponseEntity.ok(Collections.singletonMap("token", jwtUtil.generateToken(admin.getEmail()))))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid credentials")));
    }


}
