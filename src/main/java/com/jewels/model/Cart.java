package com.jewels.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "carts")
@Data
public class Cart {
    @Id
    private String id;
    private String userId; // User who owns the cart
    private List<String> productIds = new ArrayList<>(); // Initialize to an empty list

    @Data
    public static class CartItem {
        private String productId;
        private int quantity;
    }
}
