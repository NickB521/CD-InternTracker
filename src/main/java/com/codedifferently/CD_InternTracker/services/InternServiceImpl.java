package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.DailySchedule;
import com.codedifferently.CD_InternTracker.models.Intern;
import com.codedifferently.CD_InternTracker.utils.ValidationUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Pair;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class InternServiceImpl implements InternService {

    @Override
    public Intern create(Intern intern) {
        // Validate the intern data before saving
        if (!ValidationUtils.isValidIntern(intern)) {
            throw new ResourceCreationException("Invalid intern data. Ensure email is valid and fields are not empty.");
        }
        // Proceed with saving the intern
        // Save logic here (e.g., save to DB)
        return intern;
    }

    @Override
    public List<Intern> createByCSV(MultipartFile csvFile) throws Exception {
        // Implement CSV processing and validation logic here
        // Example: Loop through each intern and validate
        // If any intern fails validation, throw an exception
        return null;
    }

    @Override
    public List<Intern> getAll() {
        // Fetch and return all interns
        return null;
    }

    @Override
    public List<Intern> getByLevel(String level) {
        // Fetch interns by level
        return null;
    }

    @Override
    public Pair<Boolean, Intern> getById(Long id) {
        // Retrieve intern by ID
        return null;
    }

    @Override
    public Intern update(Long id, Intern intern) {
        // Validate intern data before updating
        if (!ValidationUtils.isValidIntern(intern)) {
            throw new ResourceCreationException("Invalid intern data. Ensure email is valid and fields are not empty.");
        }
        // Proceed with updating the intern
        return intern;
    }

    @Override
    public Intern updateInternSchedule(Long id, List<DailySchedule> internSchedule) throws ResourceNotFoundException {
        // Validate intern schedule
        return null;
    }

    @Override
    public Pair<Boolean, String> delete(Long id) {
        // Delete intern by ID
        return null;
    }
}