package com.codedifferently.CD_InternTracker.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.lang.NonNull;

import java.util.Date;

public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Date date;

    @NonNull
    private int tardiness;

    @NonNull
    private String assignment;

    @NonNull
    private String dayNotes;
}
