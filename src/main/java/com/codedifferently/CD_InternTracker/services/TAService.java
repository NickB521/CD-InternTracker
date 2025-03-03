package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.TA;
import com.codedifferently.CD_InternTracker.models.WeeklySchedule;
import com.codedifferently.CD_InternTracker.utils.ValidationUtils;

import java.util.List;


public interface TAService {

    // Create a new TA with validation
    default TA create(TA ta) throws ResourceCreationException {
        // Validate TA fields before saving
        if (!ValidationUtils.areRequiredFieldsValid(ta.getEmail(), ta.getName(), ta.getId())) {
            throw new ResourceCreationException("Invalid TA data: Please check the email, name, or ID fields.");
        }

        // Proceed with the save operation
        return saveTA(ta);
    }

    // Get a TA by their ID
    TA getById(Long id) throws ResourceNotFoundException;

    // Get a TA by their email
    TA getByEmail(String email) throws ResourceNotFoundException;

    // Get all TAs
    List<TA> getAll();

    // Update an existing TA's details with validation
    default TA update(Long id, TA taDetail) throws ResourceNotFoundException {
        // Validate TA fields before updating
        if (!ValidationUtils.areRequiredFieldsValid(taDetail.getEmail(), taDetail.getName(), taDetail.getId())) {
            throw new ResourceNotFoundException("Invalid TA data: Please check the email, name, or ID fields.");
        }

        // Proceed with the update operation
        return updateTA(id, taDetail);
    }

    // Delete a TA by ID
    void delete(Long id);

    // New method to get the Weekly Schedule of a TA
    List<WeeklySchedule> getWeeklyScheduleByTAId(Long id) throws ResourceNotFoundException;

    // Mock method for saving a single TA (for the sake of this example)
    private TA saveTA(TA ta) {
        // Save logic for a single TA (In real-world, this would interact with a database)
        return ta;
    }

    // Mock method for updating TA data
    private TA updateTA(Long id, TA ta) {
        // Update logic for an existing TA (In real-world, this would update a database record)
        return ta;
    }
}