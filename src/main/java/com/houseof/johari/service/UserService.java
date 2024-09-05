package com.houseof.johari.service;

import com.houseof.johari.model.User;
import com.houseof.johari.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User save(User user) {
        // Encrypt the user's password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the user to the repository and return the saved user
        return userRepository.save(user);
    }

    // Method to find a user by their username
    public User findByUsername(String username) {
        // Use the repository to find and return the user
        return userRepository.findByUsername(username);
    }


    // Method to find a user by their ID
    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // Method to find all users
    public List<User> findAll() {
        // Use the repository to find and return a list of all users
        return userRepository.findAll();
    }

    public User update(String id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    // Method to generate a reset token
    public String generateResetToken(User user) {
        // Generate a secure token and set expiration date
        String token =  UUID.randomUUID().toString();
        // Save token and expiration to user entity or a separate entity
        user.setResetToken(token);
        user.setResetTokenExpiry(LocalDateTime.now().plusHours(1));
        userRepository.save(user);
        return token;
    }
    // Method to validate reset token
    public boolean validateResetToken(String token, String username) {
        User user  = userRepository.findByUsername(username);
        return user != null && token.equals(user.getResetToken()) && user.getResetTokenExpiry().isAfter(LocalDateTime.now());
    }

    // Method to update password
    public void updatePassword(String username, String newPassword) {
        User user = userRepository.findByUsername(username);
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null); // Clear reset token
        user.setResetTokenExpiry(null); // Clear token expiration
        userRepository.save(user);
    }


    public boolean delete(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}