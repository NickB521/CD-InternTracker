package com.codedifferently.CD_InternTracker.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "app_user")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class TA {

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




}