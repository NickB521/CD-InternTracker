package com.codedifferently.CD_InternTracker.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TA {

<<<<<<< Updated upstream
<<<<<<< Updated upstream
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

=======
=======
>>>>>>> Stashed changes
    private String email;
    private String name;
    private String subject;

    // Constructor, getters, and setters

    public TA(String email, String name, String subject) {
        this.email = email;
        this.name = name;
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    public void setEmail(String email) {
        this.email = email;
    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
=======
=======
>>>>>>> Stashed changes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    }
}