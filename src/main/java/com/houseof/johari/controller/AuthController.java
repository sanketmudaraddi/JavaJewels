package com.houseof.johari.controller;

// Import necessary classes and dependencies
import com.houseof.johari.model.*;
import com.houseof.johari.service.JwtUserDetailsService;
import com.houseof.johari.service.UserService;
import com.houseof.johari.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

// Define the RestController for handling authentication-related requests
@RestController
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
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User registeredUser = userService.save(user);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User registration failed: " + e.getMessage());
        }
    }

    // Handler for user login and token generation
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try {
            // Authenticate the user
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            // Load user details and generate tokens
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token, refreshToken));
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

    // Handler for refreshing the authentication token
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshAuthenticationToken(@RequestBody String refreshToken) {
        try {
            // Check if the refresh token has expired
            if (jwtTokenUtil.isTokenExpired(refreshToken)) {
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("Refresh token has expired");
            }

            // Extract username from the refresh token and load user details
            String username = jwtTokenUtil.getUsernameFromToken(refreshToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Validate the refresh token and generate a new access token
            if (userDetails != null && jwtTokenUtil.validateToken(refreshToken, userDetails)) {
                String newToken = jwtTokenUtil.generateToken(userDetails);
                return ResponseEntity.ok(new JwtResponse(newToken, refreshToken));
            } else {
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("Invalid refresh token");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Token refresh failed: " + e.getMessage());
        }
    }

    // Handler for forgot password request
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        // Step 1: Validate user exists
        User user = userService.findByUsername(request.getUsername());
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        // Step 2: Generate reset token and expiration
        String resetToken = userService.generateResetToken(user);

        // Return the reset token to the user
        return ResponseEntity.ok("Your reset token is: " + resetToken);
    }

    // Handler for resetting the password
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        // Step 3: Validate reset token
        boolean validToken = userService.validateResetToken(request.getToken(), request.getUsername());
        if (!validToken) {
            return ResponseEntity.badRequest().body("Invalid or expired reset token");
        }
        // Step 4: Update user password
        userService.updatePassword(request.getUsername(), request.getNewPassword());

        return ResponseEntity.ok("Password reset successful");
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