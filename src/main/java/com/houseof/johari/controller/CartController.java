package com.houseof.johari.controller;

import com.houseof.johari.model.Cart;
import com.houseof.johari.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable String userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/add")
    public Cart addItem(@RequestParam String userId, @RequestParam String productId, @RequestParam int quantity) {
        return cartService.addItemToCart(userId, productId, quantity);
    }

    @PostMapping("/remove")
    public Cart removeItem(@RequestParam String userId, @RequestParam String productId) {
        return cartService.removeItemFromCart(userId, productId);
    }
}

