package com.vaibhav.ProductSpringWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductDB db;
    public void addProduct(Product p){
        //products.add(p);
        db.save(p);
    }

    public ArrayList<Product> getAllProducts(){
        //return products;
        return (ArrayList<Product>) db.findAll();
    }

    public ArrayList<Product> getProductsByName(String name){
        return (ArrayList<Product>) db.findByName(name);
    }

    public  ArrayList<Product> getProductsByPlace(String place) {
        return (ArrayList<Product>) db.findByPlace(place);
    }
    public  ArrayList<Product> getProductsByType(String type) {
        return (ArrayList<Product>) db.findByType(type);
    }

    public  ArrayList<Product> getProductsByWarranty(int warranty) {
        return (ArrayList<Product>) db.findByWarranty(warranty);
    }
//
    public Optional<Product> getProductById(String id) {
        return db.findById(Integer.valueOf(id));

    }


    public void deleteProductById(String id) {
        db.deleteById(Integer.valueOf(id));
    }
}
