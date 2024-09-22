package com.houseof.johari.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> getHome() {
        return ResponseEntity.ok("Welcome to the Home API");
    }

}

