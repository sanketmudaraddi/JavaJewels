package com.houseof.johari.model;

import lombok.Data;

@Data
public class CustomResponse<T> {

    private int status;
    private String message;
    private T data;


    public CustomResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}

