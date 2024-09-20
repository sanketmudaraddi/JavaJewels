package com.houseof.johari.model;

import lombok.Data;

@Data
public class ForgotPasswordRequest {
    private String username;
    private String email;

}