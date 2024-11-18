package com.jewels.controller;

import com.jewels.model.Wishlist;
import com.jewels.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Get all wishlists for a user
    @GetMapping("/{userId}")
    public List<Wishlist> getAllWishlists(@PathVariable String userId) {
        return wishlistService.getWishlistsByUserId(userId);
    }

    // Get a specific wishlist by name
    @GetMapping("/{userId}/{wishlistName}")
    public Wishlist getWishlist(@PathVariable String userId, @PathVariable String wishlistName) {
        return wishlistService.getWishlistByUserIdAndName(userId, wishlistName);
    }

    // Create a new wishlist
    @PostMapping("/create")
    public Wishlist createWishlist(@RequestParam String userId, @RequestParam String wishlistName) {
        return wishlistService.createWishlist(userId, wishlistName);
    }

    @PostMapping("/add")
    public Wishlist addItem(@RequestParam String userId, @RequestParam String wishlistName, @RequestParam String productId) {
        return wishlistService.addItemToWishlist(userId, wishlistName, productId);
    }

    @PostMapping("/remove")
    public Wishlist removeItem(@RequestParam String userId, @RequestParam String wishlistName, @RequestParam String productId) {
        return wishlistService.removeItemFromWishlist(userId, wishlistName, productId);
    }
}

