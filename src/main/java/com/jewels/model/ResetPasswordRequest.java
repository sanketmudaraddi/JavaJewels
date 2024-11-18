package com.jewels.model;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String token;
    private String username;
    private String email;
    private String newPassword;
}
