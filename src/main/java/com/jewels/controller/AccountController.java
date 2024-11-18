package com.jewels.controller;

import com.jewels.model.UserAccount;
import com.jewels.model.Order;
import com.jewels.service.UserService;
import com.jewels.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    // Get User Profile
    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserAccount> getUserProfile(@PathVariable String userId) {
        UserAccount userAccount = userService.findByAccountId(userId);
        return ResponseEntity.ok(userAccount);
    }

    // Update User Profile
    @PutMapping("/profile/{userId}")
    public ResponseEntity<UserAccount> updateUserProfile(@PathVariable String userId, @RequestBody UserAccount updatedAccount) {
        UserAccount userAccount = userService.updateUserAccount(userId, updatedAccount);
        return ResponseEntity.ok(userAccount);
    }

    // Manage Addresses
    @GetMapping("/addresses/{userId}")
    public ResponseEntity<List<String>> getUserAddresses(@PathVariable String userId) {
        List<String> addresses = userService.getUserAddresses(userId); // Implement this in UserService
        return ResponseEntity.ok(addresses);
    }

    @PostMapping("/addresses/{userId}")
    public ResponseEntity<String> addNewAddress(@PathVariable String userId, @RequestBody String newAddress) {
        String addressId = userService.addNewAddress(userId, newAddress); // Implement this in UserService
        return ResponseEntity.ok(addressId);
    }

    @PutMapping("/addresses/{userId}/{addressId}")
    public ResponseEntity<Void> editAddress(@PathVariable String userId, @PathVariable String addressId, @RequestBody String updatedAddress) {
        userService.editAddress(userId, addressId, updatedAddress); // Implement this in UserService
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/addresses/{userId}/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String userId, @PathVariable String addressId) {
        userService.deleteAddress(userId, addressId); // Implement this in UserService
        return ResponseEntity.ok().build();
    }

    // Get Current Orders
    @GetMapping("/orders/current/{userId}")
    public ResponseEntity<List<Order>> getCurrentOrders(@PathVariable String userId) {
        List<Order> currentOrders = orderService.getCurrentOrders(userId); // Implement this in OrderService
        return ResponseEntity.ok(currentOrders);
    }

    // Get Past Orders
    @GetMapping("/orders/past/{userId}")
    public ResponseEntity<List<Order>> getPastOrders(@PathVariable String userId) {
        List<Order> pastOrders = orderService.getPastOrders(userId); // Implement this in OrderService
        return ResponseEntity.ok(pastOrders);
    }
}
