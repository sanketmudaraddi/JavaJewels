package com.houseof.johari.model;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "users")
@Data
public class User {
    @Id
    @Generated
    private String id;
    private String username;
    private String password;
    private String role;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean phoneVerified; // to check if phone is verified
    private String otp;
    private LocalDateTime otpExpiry;
    private String address;
    private List<String> wishLlistProductsIds; // List of product IDs in user's wishlist
    private List<String> orderHistory; // List of order IDs
    private boolean emailVerified;
    private String preferredPaymentMethod; // e.g., Credit Card, PayPal
    private List<String> notificationPreferences; // e.g., SMS, Email

    @Field(name = "reset_token")
    private String resetToken;

    @Field(name = "reset_token_expiry")
    private LocalDateTime resetTokenExpiry;
}


// Getters and setters
    // Constructor

