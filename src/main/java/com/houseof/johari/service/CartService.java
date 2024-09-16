package com.houseof.johari.service;

import com.houseof.johari.model.Cart;
import com.houseof.johari.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart getCartByUserId(String userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart addItemToCart(String userId, String productId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
        }

        // Initialize productIds list if null
        if (cart.getProductIds() == null) {
            cart.setProductIds(new ArrayList<>());
        }

        // Add or update item logic
        if (!cart.getProductIds().contains(productId)) {
            cart.getProductIds().add(productId);
        }

        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(String userId, String productId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            // Initialize productIds list if null
            if (cart.getProductIds() != null) {
                cart.getProductIds().remove(productId);
                cartRepository.save(cart);
            }
        }
        return cart;
    }
}
