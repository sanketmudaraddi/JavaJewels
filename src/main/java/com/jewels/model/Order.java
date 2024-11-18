package com.jewels.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Data
public class Order {
    @Id
    private String id;
    private String userId; // ID of the customer who placed the order
    private List<String> productIds; // List of product IDs in this order
    private double totalAmount; // Total cost of the order
    private String paymentMethod; // e.g., Credit Card, COD, PayPal
    private String paymentStatus; // e.g., Paid, Pending, Failed
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String orderStatus; // e.g., Processing, Shipped, Delivered, Cancelled
    private String shippingAddress; // Address where the order is to be delivered
    private String trackingNumber; // Tracking number for shipment
    private List<String> couponCodes; // Applied coupons

    // Getters and setters
    // Constructor
}

