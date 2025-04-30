package com.example.cleanshelf.repository;


import com.example.cleanshelf.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/*
*JPA is used for managing data in spring boot application
* it provides CRUD operations hence ProductRepository extending JPA
* */
@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    List<Products> findByCategory(String category);
}
