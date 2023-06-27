package com.example.glovo_2_part.service;

import com.example.glovo_2_part.model.Order;
import com.example.glovo_2_part.model.Product;
import com.example.glovo_2_part.repository.OrderRepository;
import com.example.glovo_2_part.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    public final OrderRepository orderRepository;

    public final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository oderRepository, ProductRepository productRepository) {
        this.orderRepository = oderRepository;
        this.productRepository = productRepository;
    }

    public void save(Order order) {
        order.setDate(Date.from(Instant.now()));
        orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new IllegalArgumentException("The order isn't found by id");
        }
        return order;
    }

    public void deleteById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new IllegalArgumentException("The order isn't found by id");
        }
        orderRepository.deleteById(id);
    }

    @Transactional
    public void addProductToOrderById(int idProduct, int idOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(idOrder);
        Optional<Product> optionalProduct = productRepository.findById(idProduct);
        if (optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("The order isn't found by id");
        }
        if (optionalProduct.isEmpty()) {
            throw new IllegalArgumentException("The product isn't found by id");
        }
        Order order = optionalOrder.get();
        Product product = optionalProduct.get();

        order.getProducts().add(product);
        product.setOrder(order);

        orderRepository.save(order);
        productRepository.save(product);
    }

    @Transactional
    public void deleteProductFromOderById(int idProduct, int idOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(idOrder);
        Optional<Product> optionalProduct = productRepository.findById(idProduct);
        if (optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("The order isn't found by id");
        }
        if (optionalProduct.isEmpty()) {
            throw new IllegalArgumentException("The product isn't found by id");
        }
        Order order = optionalOrder.get();
        order.getProducts().removeIf(product -> product.getId() == idProduct);
        Product product = optionalProduct.get();
        product.setOrder(null);

        orderRepository.save(order);
    }
}
