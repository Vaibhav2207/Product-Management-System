package com.vaibhav.ProductSpringWeb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository

public interface ProductDB extends JpaRepository<Product,Integer> {
    ArrayList<Product> findByName(String name);
    ArrayList<Product> findByPlace(String place);
    ArrayList<Product> findByType(String type);

    ArrayList<Product> findByWarranty(int warranty);
}
