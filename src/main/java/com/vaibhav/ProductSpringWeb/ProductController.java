package com.vaibhav.ProductSpringWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController {
    @Autowired
    ProductService service;

    //This is for products request
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/product-name/{name}")
    public ResponseEntity<?> getProductName(@PathVariable String name){
        ArrayList<Product> products = service.getProductsByName(name);
        if (products.isEmpty()) {
            CustomResponse response = new CustomResponse("No product exists of this place");

            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product-place/{place}")
    public ResponseEntity<?> getProductPlace(@PathVariable String place){
        ArrayList<Product> products = service.getProductsByPlace(place);
        if (products.isEmpty()) {
            CustomResponse response = new CustomResponse("No product exists at this place");

            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product-type/{type}")
    public ResponseEntity<?> getProductType(@PathVariable String type){
        ArrayList<Product> products = service.getProductsByType(type);
        if (products.isEmpty()) {
            CustomResponse response = new CustomResponse("No product exists of this place");

            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product-warranty/{warranty}")
    public ResponseEntity<?> getProductWarranty(@PathVariable int warranty){
        ArrayList<Product> products = service.getProductsByWarranty(warranty);
        if (products.isEmpty()) {
            CustomResponse response = new CustomResponse("No product exists of this warranty");

            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product-id/{id}")
    public ResponseEntity<?> getProductId(@PathVariable String id) {
        Optional<Product> optionalProduct = service.getProductById(id);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.get());
        } else {
            CustomResponse response = new CustomResponse("Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody Product p){
        ArrayList<Product> products = service.getProductsByPlace(p.getName());
        if (!products.isEmpty()) {
            CustomResponse response = new CustomResponse("Product already exists");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }

        service.addProduct(p);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
        Optional<Product> optionalProduct = service.getProductById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            // Update the properties of existingProduct using the updatedProduct
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPlace(updatedProduct.getPlace());
            existingProduct.setType(updatedProduct.getType());
            existingProduct.setWarranty(updatedProduct.getWarranty());
            service.addProduct(existingProduct);

            return ResponseEntity.ok(existingProduct);
        } else {
            CustomResponse response = new CustomResponse("Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        Optional<Product> optionalProduct = service.getProductById(id);
        if (optionalProduct.isPresent()) {
            service.deleteProductById(id);
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            CustomResponse response = new CustomResponse("Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
class CustomResponse {
    private String message;

    public CustomResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}