package com.codedifferently.CD_InternTracker.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TA {

    private Long id;
    private String username;
    private String email;
    private String name;
    private boolean isActive;
    private boolean isAdmin;
    private String password;  // Assuming there's a field for password

    // Other fields and methods

    public String getPassword() {
        return password;
    }

    // Constructor with new fields
    public TA(String username, String email, String name, boolean isActive, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.isActive = isActive;
        this.isAdmin = isAdmin;
    }

    // Getters and setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}