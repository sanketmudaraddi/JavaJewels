package com.houseof.johari.repository;

import com.houseof.johari.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {
    Cart findByUserId(String userId);
}

