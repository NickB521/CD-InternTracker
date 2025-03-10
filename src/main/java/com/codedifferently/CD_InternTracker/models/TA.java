package com.codedifferently.CD_InternTracker.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


public class TA {


    private Long id;

    private String name;

    private String email;
    private String subject;
    private String password;
    private String phoneNumber;
    private boolean isAdmin;
    private boolean isTA;



    private List<WeeklySchedule> weeklySchedule;






    public TA(String email, String name, String subject, String password, String phoneNumber, boolean isAdmin, boolean isTA) {
        this.email = email;
        this.name = name;
        this.subject = subject;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
        this.isTA = isTA;
    }


    // Getter and Setter Methods for each property

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isTA() {
        return isTA;
    }

    public void setTA(boolean TA) {
        isTA = TA;
    }
    public List<WeeklySchedule> getWeeklySchedule() {
        return weeklySchedule;
    }

    public void setWeeklySchedule(List<WeeklySchedule> weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }
}



