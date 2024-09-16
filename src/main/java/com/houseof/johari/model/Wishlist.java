package com.houseof.johari.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "wishlists")
@Data
public class Wishlist {
    @Id
    private String id;
    private String userId; // User who owns the wishlist
    private List<String> productIds; // List of product IDs in the wishlist
}