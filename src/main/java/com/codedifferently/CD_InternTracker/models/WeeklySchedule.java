package com.codedifferently.CD_InternTracker.models;

import java.time.LocalTime;
import java.util.List;

public class WeeklySchedule {
    private Long id;  // Add ID field
    private String dayOfWeek;  // e.g., Monday, Tuesday, etc.
    private LocalTime startTime;  // e.g., 08:00 AM
    private LocalTime endTime;  // e.g., 12:00 PM
    private List<String> attachments; // List of attachment URLs or paths

    // Constructor
    public WeeklySchedule(Long id, String dayOfWeek, LocalTime startTime, LocalTime endTime, List<String> attachments) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.attachments = attachments;
    }

    // Getters and Setters
    public Long getId() {  // Add getId method
        return id;
    }

    public void setId(Long id) {  // Add setId method
        this.id = id;
    }

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

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "WeeklySchedule{" +
                "id=" + id +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", attachments=" + attachments +
                '}';
    }
}