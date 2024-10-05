package com.houseof.johari.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String gender;
    private String category;
    private boolean bestSeller;
    private String brand;
    private String imageUrl;
    private double rating; // Average rating based on user reviews
    private String material; // e.g., Gold, Silver, Platinum
    private String gemType; // e.g., Diamond, Ruby, Sapphire
    private String metalPurity; // e.g., 18K, 24K for gold
    private double weight; // in grams
    private String size; // e.g., ring size or necklace length
    private List<String> colors; // Available colors
    private boolean inStock; // Availability status
    private List<String> tags; // e.g., trending, bestseller
    private boolean isNewArrival;
    private String occasion;
    private boolean hasOffer;
    private double discountPrice; // Discounted price in case of an offer
    private String recentlyViewed;

    // Getters and setters
    // Constructor
}
