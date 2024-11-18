package com.jewels.model;

import lombok.Data;

@Data
public class ForgotPasswordRequest {
    private String username;
    private String email;

}