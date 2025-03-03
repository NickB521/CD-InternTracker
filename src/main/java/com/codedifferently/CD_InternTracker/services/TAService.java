package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.TA;
import com.codedifferently.CD_InternTracker.models.WeeklySchedule;

import java.util.List;


public interface TAService {

    // Create a new TA with validation
    TA create(TA ta) throws ResourceCreationException;

    // Get a TA by their ID
    TA getById(Long id) throws ResourceNotFoundException;

    // Get a TA by their email
    TA getByEmail(String email) throws ResourceNotFoundException;

    // Get all TAs
    List<TA> getAll();

    // Update an existing TA's details with validation
    TA update(Long id, TA taDetail) throws ResourceNotFoundException;

    // Delete a TA by ID
    void delete(Long id);

    // Get the Weekly Schedule for a TA
    List<WeeklySchedule> getWeeklyScheduleByTAId(Long id) throws ResourceNotFoundException;

    // Add Weekly Schedule for a TA
    void addWeeklySchedule(Long id, WeeklySchedule weeklySchedule) throws ResourceNotFoundException;

    // Remove Weekly Schedule for a TA
    void removeWeeklySchedule(Long id, Long scheduleId) throws ResourceNotFoundException;
}