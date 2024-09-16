package com.houseof.johari.service;

import com.houseof.johari.model.Wishlist;
import com.houseof.johari.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public Wishlist getWishlistByUserId(String userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public Wishlist addItemToWishlist(String userId, String productId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId);
        if (wishlist == null) {
            // Create a new wishlist if it doesn't exist
            wishlist = new Wishlist();
            wishlist.setUserId(userId);
        }
        // Initialize productIds list if it's null
        if (wishlist.getProductIds() == null) {
            wishlist.setProductIds(new ArrayList<>());
        }
        // Add productId to the list if it's not already present
        if (!wishlist.getProductIds().contains(productId)) {
            wishlist.getProductIds().add(productId);
        }
        return wishlistRepository.save(wishlist);
    }

    public Wishlist removeItemFromWishlist(String userId, String productId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId);
        if (wishlist != null && wishlist.getProductIds() != null) {
            // Remove productId from the list if it exists
            wishlist.getProductIds().remove(productId);
            // Save the updated wishlist
            wishlistRepository.save(wishlist);
        }
        return wishlist;
    }
}
