package com.codedifferently.CD_InternTracker.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.lang.NonNull;


public class DailySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String weekDay;

    @NonNull
    private String startTime;

    @NonNull
    private String endTime;

    // Default constructor
    public DailySchedule() {
    }