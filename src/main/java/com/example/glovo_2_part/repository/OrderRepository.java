package com.example.glovo_2_part.repository;

import com.example.glovo_2_part.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository <Order, Integer> {

}