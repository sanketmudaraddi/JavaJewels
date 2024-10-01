package com.houseof.johari.model;

import lombok.Data;

import java.util.List;

@Data
public class FAQResponse {
    private List<FAQ> faqs;

    // Inner class for FAQ
    @Data
    public static class FAQ {
        private String question;
        private String answer;
    }
}

