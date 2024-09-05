package com.houseof.johari.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "reviews")
@Data
public class Review {
    @Id
    private String id;
    private String productId;
    private String userId;
    private String reviewText;
    private double rating;
    private LocalDateTime reviewDate;
    private List<String> images; // Images uploaded with the review
    private boolean verifiedPurchase; // Indicates if the user purchased the item
    private int helpfulVotes; // Number of times the review was marked as helpful

    // Getters and setters
    // Constructor
}

