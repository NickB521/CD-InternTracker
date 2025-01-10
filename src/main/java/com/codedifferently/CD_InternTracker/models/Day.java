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

    public Long getId() {
        return id;
    };
    public void setId(Long id) {
        this.id = id;
    };

    @NonNull
    public Date getDate() {
        return date;
    }
    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    public int getTardiness() {
        return tardiness;
    }
    public void setTardiness(int tardiness) {
        this.tardiness = tardiness;
    }

    @NonNull
    public String getAssignment() {
        return assignment;
    }
    public void setAssignment(@NonNull String assignment) {
        this.assignment = assignment;
    }

    @NonNull
    public String getDayNotes() {
        return dayNotes;
    }

    public void setDayNotes(@NonNull String dayNotes) {
        this.dayNotes = dayNotes;
    }
}
