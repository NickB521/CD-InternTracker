package com.codedifferently.CD_InternTracker.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TA {

    private String name;
    private String email;
    private Long id;

    // Constructor to initialize TA with name, email, and id
    public TA(String name, String email, Long id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    // Getters and setters for name, email, and id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}