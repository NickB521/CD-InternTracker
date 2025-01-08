package com.codedifferently.CD_InternTracker.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.lang.NonNull;

public class User {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NonNull
private String password;

@NonNull
    private String email;

@NonNull
    private String phoneNumber;

@NonNull
    private String name;

@NonNull
    private boolean isAdmin;

@NonNull
    private boolean isTA;

//need to implement the list of interns into this file.
}
