package com.houseof.johari.repository;

import com.houseof.johari.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    Wishlist findByUserId(String userId);
}
