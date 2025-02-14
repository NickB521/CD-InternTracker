package com.codedifferently.CD_InternTracker.models;

import jakarta.persistence.*;

@Entity
public class Intern {

    private String name;
    private String email;
    private Long id;

    // Constructor to initialize Intern with name, email, and id
    public Intern(String name, String email, Long id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    // Getters and setters
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

    // You can remove the duplicate setId method
    public void setId(Long id) {
        this.id = id;
    }
}