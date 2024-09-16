package com.houseof.johari.controller;

import com.houseof.johari.model.Wishlist;
import com.houseof.johari.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @GetMapping("/{userId}")
    public Wishlist getWishlist(@PathVariable String userId) {
        return wishlistService.getWishlistByUserId(userId);
    }

    @PostMapping("/add")
    public Wishlist addItem(@RequestParam String userId, @RequestParam String productId) {
        return wishlistService.addItemToWishlist(userId, productId);
    }

    @PostMapping("/remove")
    public Wishlist removeItem(@RequestParam String userId, @RequestParam String productId) {
        return wishlistService.removeItemFromWishlist(userId, productId);
    }
}

