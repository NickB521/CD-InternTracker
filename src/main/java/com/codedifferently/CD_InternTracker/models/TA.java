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
    private Long id;  // Make sure the type of id is Long (or whatever type you use in your DB)
    private String password;
    private String email;
    private String phoneNumber;
    private String name;
    private boolean isAdmin;
    private boolean isTA;

    // Getter and Setter methods for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter methods for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter methods for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter methods for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter methods for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter methods for isAdmin
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    // Getter and Setter methods for isTA
    public boolean isTA() {
        return isTA;
    }

    public void setTA(boolean isTA) {
        this.isTA = isTA;
    }
}