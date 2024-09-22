package com.houseof.johari.controller;

import com.houseof.johari.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home/about")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    // Get About Us information
    @GetMapping
    public ResponseEntity<String> getAboutUs() {
        String aboutInfo = aboutService.getAboutUsInfo();
        return ResponseEntity.ok(aboutInfo);
    }

    // Get Privacy Policy
    @GetMapping("/privacy-policy")
    public ResponseEntity<String> getPrivacyPolicy() {
        String privacyPolicy = aboutService.getPrivacyPolicy();
        return ResponseEntity.ok(privacyPolicy);
    }

    // Get Contact Us information
    @GetMapping("/contact-us")
    public ResponseEntity<String> getContactUs() {
        String contactInfo = aboutService.getContactUs();
        return ResponseEntity.ok(contactInfo);
    }

    // Get FAQs
    @GetMapping("/faqs")
    public ResponseEntity<String> getFAQs() {
        String faqs = aboutService.getFAQs();
        return ResponseEntity.ok(faqs);
    }

    // Get Terms and Conditions
    @GetMapping("/terms-and-conditions")
    public ResponseEntity<String> getTermsAndConditions() {
        String termsAndConditions = aboutService.getTermsAndConditions();
        return ResponseEntity.ok(termsAndConditions);
    }
}

