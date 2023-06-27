package com.example.glovo_2_part.service;

import com.example.glovo_2_part.model.Product;
import com.example.glovo_2_part.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    public Optional<Product> findByName(String name) {
        return productRepository.findProductByName(name);
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void deleteByName(String name) {
        productRepository.deleteProductByName(name);
    }


}
