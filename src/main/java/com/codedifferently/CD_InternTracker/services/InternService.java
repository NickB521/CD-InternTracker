package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.DailySchedule;
import com.codedifferently.CD_InternTracker.models.Intern;
import com.opencsv.CSVReader;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.web.multipart.MultipartFile;
import com.codedifferently.CD_InternTracker.utils.ValidationUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public interface InternService {

    // Create a new Intern with validation
    Intern create(Intern intern);

//    // Create multiple Interns from a CSV file
//    public List<Intern> createByCSV(MultipartFile csvFile) throws Exception {
//        List<Intern> parsedInterns = new ArrayList<>();
//
//        try (CSVReader reader = new CSVReader(new InputStreamReader(csvFile.getInputStream()))) {
//            String[] nextLine;
//            while ((nextLine = reader.readNext()) != null) {
//                String name = nextLine[0];
//                String email = nextLine[1];
//                Long id = Long.parseLong(nextLine[2]);
//
//                // Create a new intern object and add it to the list
//                Intern intern = new Intern(name, email, id);
//                parsedInterns.add(intern);
//            }
//        } catch (IOException e) {
//            throw new IOException("Error parsing CSV file", e);
//        }
//
//        // Validate all intern entries (after parsing CSV)
//        for (Intern intern : parsedInterns) {
//            if (!ValidationUtils.areRequiredFieldsValid(intern.getEmail(), intern.getName(), intern.getId())) {
//                throw new IllegalArgumentException("Invalid Intern data in CSV: One or more fields are incorrect.");
//            }
//        }
//
//        // Proceed with saving the list of interns
//        return saveInterns(parsedInterns);
//    }

    // Get all Interns
    List<Intern> getAll();

    // Get Interns by their level
    List<Intern> getByLevel(String level);

    // Get an Intern by ID
    Pair<Boolean, Intern> getById(Long id);

    // Update an existing Intern's details
    default Intern update(Long id, Intern intern) {
        // Validate intern fields before updating
        if (!ValidationUtils.areRequiredFieldsValid(intern.getEmail(), intern.getName(), intern.getId())) {
            throw new IllegalArgumentException("Invalid Intern data: Please check the email, name, or ID fields.");
        }

        // Proceed with the update operation
        return updateIntern(id, intern);
    }

    // Update the Intern's schedule
    Intern updateInternSchedule(Long id, List<DailySchedule> internSchedule) throws ResourceNotFoundException;

    // Delete an Intern by ID
    Pair<Boolean, String> delete(Long id);

    // Mock method for saving a single intern (for the sake of this example)
    private Intern saveIntern(Intern intern) {
        // Save logic for a single intern
        return intern;  // This would normally save to a database
    }

    // Mock method for saving multiple interns
    private List<Intern> saveInterns(List<Intern> interns) {
        // Save logic for multiple interns
        return interns;  // This would normally save to a database
    }

    // Mock method for updating intern data
    private Intern updateIntern(Long id, Intern intern) {
        // Update logic for an existing intern
        return intern;  // This would normally update the database record
    }
}