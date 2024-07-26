package com.indusnet.socialmedia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;  // Change to String for MongoDB
    private String username;
    private String password;
    // Other fields...
}
