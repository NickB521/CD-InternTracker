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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    private boolean isAdmin;
    private boolean isTA;

    public TA(String name, String email, String password, String phoneNumber, boolean isAdmin, boolean isTA) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
        this.isTA = isTA;
    }
}