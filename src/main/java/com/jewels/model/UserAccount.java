package com.jewels.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
public class UserAccount {
    @Id
    private String accountId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<String> addresses; // List of addresses

    // Getters and Setters

}
