package com.codedifferently.CD_InternTracker.models;

import java.util.List;

public class Intern {

    private Long id;
    private String name;
    private String email;
    private String level;
    private List<DailySchedule> weeklySchedule;
    private String internNotes;
    private List<String> attendance;

    // Constructor
    public Intern(String name, String email, Long id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    // No-argument constructor for test purposes
    public Intern() {
        // Default constructor for creating empty instances
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<DailySchedule> getWeeklySchedule() {
        return weeklySchedule;
    }

    public void setWeeklySchedule(List<DailySchedule> weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }

    public String getInternNotes() {
        return internNotes;
    }

    public void setInternNotes(String internNotes) {
        this.internNotes = internNotes;
    }

    public List<String> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<String> attendance) {
        this.attendance = attendance;
    }
}