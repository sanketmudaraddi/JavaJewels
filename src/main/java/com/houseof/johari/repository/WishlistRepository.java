package com.houseof.johari.repository;

import com.houseof.johari.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    // Find all wishlists for a specific user
    List<Wishlist> findAllByUserId(String userId);

    // Find a specific wishlist by userId and wishlistName
    Wishlist findByUserIdAndWishlistName(String userId, String wishlistName);
}

