package com.example.glovo_2_part.controller;

import com.example.glovo_2_part.model.Product;
import com.example.glovo_2_part.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product> findById(@PathVariable int id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product.get());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Product> findByName(@PathVariable String name) {
        Optional<Product> product = productService.findByName(name);
        if (!product.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product.get());
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable int id) {
        productService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<HttpStatus> deleteByName(@PathVariable String name) {
        productService.deleteByName(name);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
