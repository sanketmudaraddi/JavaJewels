package com.jewels.model;

import lombok.Data;

import java.io.Serializable;

// Model class representing a JWT request
@Data
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    // Username for authentication
    private String username;
    // Password for authentication
    private String password;

    // Default constructor for JSON Parsing
    public JwtRequest() {
    }

    // Parameterized constructor for creating a JwtRequest object
    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    // Getter for username
}