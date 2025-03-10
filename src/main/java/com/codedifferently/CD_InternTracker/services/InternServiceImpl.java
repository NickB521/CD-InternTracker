package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.DailySchedule;
import com.codedifferently.CD_InternTracker.models.Intern;
import com.codedifferently.CD_InternTracker.storage.JsonDataStorage;
import com.codedifferently.CD_InternTracker.utils.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Service
public class InternServiceImpl implements InternService {

    private List<Intern> interns;
    private static final Logger logger = LoggerFactory.getLogger(InternServiceImpl.class);

    public InternServiceImpl() {
        loadData();
    }

    private void loadData() {
        interns = (List<Intern>) JsonDataStorage.loadInterns();
        logger.info("Interns data loaded successfully.");
    }

    private void saveData() {
        JsonDataStorage.saveInterns(this.interns);
        logger.info("Interns data saved successfully.");
    }

    @Override
    public Intern create(Intern intern) {
        logger.debug("Creating intern with email: {}", intern.getEmail());

        // Validate the intern data before saving
        if (!ValidationUtils.isValidIntern(intern)) {
            logger.error("Invalid intern data for email: {}", intern.getEmail());
            throw new ResourceCreationException("Invalid intern data. Ensure email is valid and fields are not empty.");
        }

        // Check if intern with the same email already exists
        Optional<Intern> existingIntern = interns.stream()
                .filter(i -> i.getEmail().equals(intern.getEmail()))
                .findFirst();

        if (existingIntern.isPresent()) {
            logger.error("Intern with email {} already exists.", intern.getEmail());
            throw new ResourceCreationException("Intern with email exists: " + intern.getEmail());
        }

        // Add intern and save
        interns.add(intern);
        saveData();
        logger.info("Intern created successfully with email: {}", intern.getEmail());
        return intern;
    }

//    @Override
//    public List<Intern> createByCSV(MultipartFile csvFile) throws Exception {
//        // Implement CSV processing and validation logic here
//        logger.info("Processing CSV file to create interns.");
//        return null;
//    }

    @Override
    public List<Intern> getAll() {
        logger.info("Fetching all interns.");
        return interns;
    }

    @Override
    public List<Intern> getByLevel(String level) {
        logger.info("Fetching interns by level: {}", level);
        return null;
    }

    @Override
    public Pair<Boolean, Intern> getById(Long id) {
        logger.info("Fetching intern by ID: {}", id);
        Intern intern = interns.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
        return new Pair<>(intern != null, intern);
    }

    @Override
    public Intern update(Long id, Intern intern) {
        logger.debug("Updating intern with ID: {}", id);

        // Validate intern data before updating
        if (!ValidationUtils.isValidIntern(intern)) {
            logger.error("Invalid intern data for ID: {}", id);
            throw new ResourceCreationException("Invalid intern data. Ensure email is valid and fields are not empty.");
        }

        // Find and update intern
        Intern existingIntern = interns.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No intern found with ID: " + id));

        existingIntern.setName(intern.getName());
        existingIntern.setEmail(intern.getEmail());
        existingIntern.setInternNotes(intern.getInternNotes());
        existingIntern.setAttendance(intern.getAttendance());
        existingIntern.setWeeklySchedule(intern.getWeeklySchedule());

        saveData();
        logger.info("Intern with ID {} updated successfully.", id);
        return existingIntern;
    }

    @Override
    public Intern updateInternSchedule(Long id, List<DailySchedule> internSchedule) throws ResourceNotFoundException {
        logger.debug("Updating intern schedule for ID: {}", id);

        // Find and update intern's schedule
        Intern intern = interns.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No intern found with ID: " + id));

        intern.setWeeklySchedule(internSchedule);
        saveData();
        logger.info("Intern schedule updated for ID: {}", id);
        return intern;
    }

    @Override
    public Pair<Boolean, String> delete(Long id) {
        logger.debug("Deleting intern with ID: {}", id);

        boolean removed = interns.removeIf(intern -> intern.getId().equals(id));
        saveData();
        String message = removed ? "Intern deleted successfully" : "Intern not found";
        logger.info(message);
        return new Pair<>(removed, message);
    }
}