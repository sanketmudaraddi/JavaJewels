package com.houseof.johari.service;

import com.houseof.johari.model.Wishlist;
import com.houseof.johari.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    // Get all wishlists for a user
    public List<Wishlist> getWishlistsByUserId(String userId) {
        return wishlistRepository.findAllByUserId(userId);
    }

    // Get a specific wishlist by userId and wishlistName
    public Wishlist getWishlistByUserIdAndName(String userId, String wishlistName) {
        return wishlistRepository.findByUserIdAndWishlistName(userId, wishlistName);
    }

    // Create a new wishlist
    public Wishlist createWishlist(String userId, String wishlistName) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(userId);
        wishlist.setWishlistName(wishlistName);
        wishlist.setProductIds(new ArrayList<>());  // Initialize empty product list
        return wishlistRepository.save(wishlist);
    }

    // Add item to an existing wishlist
    public Wishlist addItemToWishlist(String userId, String wishlistName, String productId) {
        Wishlist wishlist = wishlistRepository.findByUserIdAndWishlistName(userId, wishlistName);
        if (wishlist == null) {
            throw new IllegalArgumentException("Wishlist not found");
        }
        if (!wishlist.getProductIds().contains(productId)) {
            wishlist.getProductIds().add(productId);
        }
        return wishlistRepository.save(wishlist);
    }


    // Remove item from an existing wishlist
    public Wishlist removeItemFromWishlist(String userId, String wishlistName, String productId) {
        Wishlist wishlist = wishlistRepository.findByUserIdAndWishlistName(userId, wishlistName);
        if (wishlist != null && wishlist.getProductIds() != null) {
            wishlist.getProductIds().remove(productId);
            wishlistRepository.save(wishlist);
        }
        return wishlist;
    }
}
