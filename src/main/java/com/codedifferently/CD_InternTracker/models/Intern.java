package com.codedifferently.CD_InternTracker.models;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
public class Intern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private String phoneNumber;

    private String internNotes;
    @ElementCollection
    private List<DailySchedule> weeklySchedule;
    @NonNull
    public Long getId() {return id;}

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }


    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInternNotes() {
        return internNotes;
    }

    public void setInternNotes(String internNotes) {
        this.internNotes = internNotes;
    }
    public List<DailySchedule> getWeeklySchedule() {
        return weeklySchedule;
    }

    public void setWeeklySchedule(List<DailySchedule> weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }
}