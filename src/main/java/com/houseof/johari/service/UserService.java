package com.houseof.johari.service;

import com.houseof.johari.model.User;
import com.houseof.johari.model.UserAccount;
import com.houseof.johari.repository.UserAccountRepository;
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

    @Autowired
    private UserAccountRepository userAccountRepository;

    public String generateOTP(User user) {
        //generate random 6 digit number
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000);
        user.setOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(10));  // OTP is valid for 10 minutes
        userRepository.save(user);

        return otp;

    }

    public boolean verifyOTP(String phoneNumber, String otp) {

        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null && user.getOtp().equals(otp) && user.getOtpExpiry().isAfter(LocalDateTime.now())) {
            user.setPhoneVerified(true);
            user.setOtp(null); // Clear the OTP once verified
            user.setOtpExpiry(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

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
// Method to find a user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Method to find a user by their ID
    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
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
        String token = UUID.randomUUID().toString();
        // Save token and expiration to user entity or a separate entity
        user.setResetToken(token);
        user.setResetTokenExpiry(LocalDateTime.now().plusHours(1));
        userRepository.save(user);
        return token;
    }

    // Method to validate reset token
    public boolean validateResetToken(String token, String username) {
        User user = userRepository.findByUsername(username);
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

    public User updateProfile(String id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setAddress(updatedUser.getAddress());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setPreferredPaymentMethod(updatedUser.getPreferredPaymentMethod());
            return userRepository.save(user);
        }
        return null;
    }


    public boolean delete(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public UserAccount findByAccountId(String userId) {
        return userAccountRepository.findById(userId).orElse(null);
    }

    public UserAccount updateUserAccount(String userId, UserAccount updatedAccount) {
        updatedAccount.setAccountId(userId); // Ensure the ID remains the same
        return userAccountRepository.save(updatedAccount);
    }

    public List<String> getUserAddresses(String userId) {
        UserAccount userAccount = findByAccountId(userId);
        return userAccount != null ? userAccount.getAddresses() : null;
    }

    public String addNewAddress(String userId, String newAddress) {
        UserAccount userAccount = findByAccountId(userId);
        if (userAccount != null) {
            userAccount.getAddresses().add(newAddress);
            userAccountRepository.save(userAccount);
            return newAddress; // Return the new address added
        }
        return null;
    }

    public void editAddress(String userId, String addressId, String updatedAddress) {
        UserAccount userAccount = findByAccountId(userId);
        if (userAccount != null && userAccount.getAddresses().contains(addressId)) {
            int index = userAccount.getAddresses().indexOf(addressId);
            userAccount.getAddresses().set(index, updatedAddress);
            userAccountRepository.save(userAccount);
        }
    }

    public void deleteAddress(String userId, String addressId) {
        UserAccount userAccount = findByAccountId(userId);
        if (userAccount != null) {
            userAccount.getAddresses().remove(addressId);
            userAccountRepository.save(userAccount);
        }
    }
}