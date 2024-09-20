package com.houseof.johari.repository;

import com.houseof.johari.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    // Custom query methods if needed
    List<Order> findCurrentOrdersByUserId(String userId);
    List<Order> findPastOrdersByUserId(String userId);
}
