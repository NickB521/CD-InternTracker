package com.codedifferently.CD_InternTracker.models;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data



@Table(name = "app_user")
public class TA {

    private String username;
    private String email;
    private String phone;
    private String name;
    private boolean isAdmin;
    private boolean isActive;

    // Constructor
    public TA(String username, String email, String phone, String name, boolean isAdmin, boolean isActive) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
    }

    // Getters and setters (if needed)
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}