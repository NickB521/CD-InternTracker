package com.codedifferently.CD_InternTracker.models;

import jakarta.persistence.*;

@Entity
public class Intern {

    private Long id;
    private String name;
    private String email;
    private String level; // assuming this is another field you might want

    // Constructor
    public Intern(String name, String email, Long id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    // Optionally, you can add setters for level if necessary
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}