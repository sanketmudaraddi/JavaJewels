package com.houseof.johari.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "searchHistory")
@Data
public class SearchHistory {
    @Id
    private String id;
    private String userId;
    private String searchTerm;
    private LocalDateTime timestamp;

    // Getters and Setters
}
