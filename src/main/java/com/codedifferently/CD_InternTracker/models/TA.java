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
    private String createdBy;

@NonNull
    private boolean isAdmin;

@NonNull
    private boolean isTA;

    @ElementCollection
    private List<Intern> internList;



//need to implement the list of interns into this file.
}
