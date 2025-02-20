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

    public TA(String email, String name, String subject, String password, String phoneNumber, boolean isAdmin, boolean isTA) {
        this.email = email;
        this.name = name;
        this.subject = subject;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
        this.isTA = isTA;
    }
}
