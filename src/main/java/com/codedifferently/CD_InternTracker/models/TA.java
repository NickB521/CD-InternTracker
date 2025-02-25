package com.codedifferently.CD_InternTracker.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String subject;
    private String password;
    private String phoneNumber;
    private boolean isAdmin;
    private boolean isTA;

    // No-argument constructor
    public TA() {
    }

    // Add this constructor
    public TA(String password, String email, String phoneNumber, String name, boolean isAdmin, boolean isTA) {
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.isAdmin = isAdmin;
        this.isTA = isTA;
    }

    // Getter methods for id, name, and email
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

