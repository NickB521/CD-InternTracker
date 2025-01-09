package com.codedifferently.CD_InternTracker.models;


import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

@Embeddable
public class DailySchedule {

    @NonNull
    private String weekDay;

    @NonNull
    private String startTime;

    @NonNull
    private String endTime;

    // Default constructor
    public DailySchedule() {
    }


    // Constructor with parameters
    public DailySchedule(String weekDay, String startTime, String endTime) {
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and setters

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
