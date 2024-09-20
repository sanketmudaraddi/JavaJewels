package com.houseof.johari.model;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String token;
    private String username;
    private String email;  // Change from 'username' to 'email'
    private String newPassword;
}
