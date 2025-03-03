package com.codedifferently.CD_InternTracker.models;

import java.time.LocalTime;

public class WeeklySchedule {
    private String dayOfWeek;  // e.g., Monday, Tuesday, etc.
    private LocalTime startTime;  // e.g., 08:00 AM
    private LocalTime endTime;  // e.g., 12:00 PM

    // Constructor
    public WeeklySchedule(String dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "WeeklySchedule{" +
                "dayOfWeek='" + dayOfWeek + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
