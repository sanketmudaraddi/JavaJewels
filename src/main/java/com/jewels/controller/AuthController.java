package com.jewels.controller;

import com.jewels.model.*;
import com.jewels.service.JwtUserDetailsService;
import com.jewels.service.UserService;
import com.jewels.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// Define the RestController for handling authentication-related requests
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Autowire necessary dependencies
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    // Handler for user registration
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null) {
            response.put("error", "Email already registered.");
            return ResponseEntity.badRequest().body(response);
        }
        // Complete the registration process
        user.setPassword(user.getPassword());  // You should encode the password
        userService.save(user);

        response.put("message", "User registered successfully.");
        return ResponseEntity.ok(response);
    }

    // Commented out the OTP-related feature for now
    /*
    // Handler for sending OTP
    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null && existingUser.isPhoneVerified()) {
            response.put("error", "Email already verified.");
            return ResponseEntity.badRequest().body(response);
        }

        // Generate OTP and send it via email (or other method)
        String otp = userService.generateOTP(user);
        response.put("message", "OTP sent to " + user.getEmail());
        return ResponseEntity.ok(response);
    }

    // Step 2: Verify OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        Map<String, String> response = new HashMap<>();
        boolean isVerified = userService.verifyOTP(email, otp);
        if (isVerified) {
            response.put("message", "Email verified successfully.");
            return ResponseEntity.ok(response);
        }
        response.put("error", "Invalid OTP or OTP expired.");
        return ResponseEntity.badRequest().body(response);
    }
    */

    // Handler for user login and token generation
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try {
            // Authenticate the user
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            // Load user details and generate tokens
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token));
        } catch (DisabledException e) {
            // If the user account is disabled
            return ResponseEntity.badRequest().body(Map.of("error", "USER_DISABLED"));
        } catch (BadCredentialsException e) {
            // If the credentials are invalid
            return ResponseEntity.badRequest().body(Map.of("error", "INVALID_CREDENTIALS"));
        } catch (Exception e) {
            // Catch all other errors
            return ResponseEntity.badRequest().body(Map.of("error", "LOGIN_FAILED"));
        }
    }

    // Handler for forgot password request
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        Map<String, String> response = new HashMap<>();
        // Step 1: Validate user exists
        User user = userService.findByEmail(request.getEmail());
        if (user == null) {
            response.put("error", "User not found");
            return ResponseEntity.badRequest().body(response);
        }

        // Step 2: Generate reset token and expiration
        String resetToken = userService.generateResetToken(user);

        // Return the reset token to the user
        response.put("message", "Your reset token is: " + resetToken);
        return ResponseEntity.ok(response);
    }

    // Handler for resetting the password
    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody ResetPasswordRequest request) {
        Map<String, String> response = new HashMap<>();
        // Step 3: Validate reset token
        boolean validToken = userService.validateResetToken(request.getToken(), request.getEmail());
        if (!validToken) {
            response.put("error", "Invalid or expired reset token");
            return ResponseEntity.badRequest().body(response);
        }
        // Step 4: Update user password
        userService.updatePassword(request.getEmail(), request.getNewPassword());

        response.put("message", "Password reset successful");
        return ResponseEntity.ok(response);
    }

    // Helper method to authenticate user credentials
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
