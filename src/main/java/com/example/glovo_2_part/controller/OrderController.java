package com.example.glovo_2_part.controller;

import com.example.glovo_2_part.model.Order;
import com.example.glovo_2_part.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody Order order) {
        orderService.save(order);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Order> findById(@PathVariable int id) {
        return orderService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        orderService.deleteById(id);
        return ResponseEntity.ok("The remove is successful");
    }

    @PostMapping("/{idOrder}/addProduct")
    public ResponseEntity<String> addProductToOrderById(@PathVariable int idOrder, @RequestBody Map<String, Integer> product) {
        orderService.addProductToOrderById(product.get("productId"), idOrder);
        return ResponseEntity.ok("Product was added to Order");
    }

    @DeleteMapping ("/{idOrder}/deleteProduct")
    public ResponseEntity<String> deleteProductFromOderById(@PathVariable int idOrder, @RequestBody Map<String, Integer> product) {
        orderService.deleteProductFromOderById(product.get("productId"), idOrder);
        return ResponseEntity.ok("Product was removed to Order");
    }
}


