package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.logging.LoggingConfig;
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
//create logger
    private static final Logger logger
            = LoggerFactory.getLogger(InternServiceImpl.class);
    private List<Intern> interns;

    public InternServiceImpl() {
        loadData();
    }

    private void loadData() {
        interns = (List<Intern>) JsonDataStorage.loadData().get("interns");
    }

    private void saveData() {
        JsonDataStorage.saveData(interns, null);
    }

    @Override
    public Intern create(Intern intern) {

        // Validate the intern data before saving
        if (!ValidationUtils.isValidIntern(intern)) {
            //logs exception
            logger.warn("Resource creation exception in intern create (invalid data submitted)");
            throw new ResourceCreationException("Invalid intern data. Ensure email is valid and fields are not empty.");


        }

        // Check if intern with the same email already exists
        Optional<Intern> existingIntern = interns.stream()
                .filter(i -> i.getEmail().equals(intern.getEmail()))
                .findFirst();

        if (existingIntern.isPresent()) {
            //logs exception
            logger.warn("Resource creation exception in intern create (intern with email exists)");
            throw new ResourceCreationException("Intern with email exists: " + intern.getEmail());
        }

        // Add intern and save
        interns.add(intern);
        saveData();
        //logs save occurring.
        logger.info("Intern created");

        return intern;
    }

    @Override
    public List<Intern> createByCSV(MultipartFile csvFile) throws Exception {
        // logs occurrence of use
        logger.info("intern imported from CSV");

        //this logger was implemented before  this function was fully built. contact Colin Feeley to further build on this log.

        // Implement CSV processing and validation logic here
        return null;
    }

    @Override
    public List<Intern> getAll()
    {
        // logs occurrence of use
        logger.info("got all interns");

        return interns;
    }

    @Override
    public List<Intern> getByLevel(String level) {
        // logs occurrence of use
        logger.info("got intern by level");
        // Fetch interns by level
        return null;
    }

    @Override
    public Pair<Boolean, Intern> getById(Long id) {
        Intern intern = interns.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
        // logs occurrence of use
        logger.info("got intern by id");
        return new Pair<>(intern != null, intern);
    }

    @Override
    public Intern update(Long id, Intern intern) {
        // Validate intern data before updating
        if (!ValidationUtils.isValidIntern(intern)) {
            //logs exception
            logger.warn("Resource creation exception in intern update (invalid data submitted)");
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
        // logs occurence of use
        logger.info("update intern was used");
        return existingIntern;
    }

    @Override
    public Intern updateInternSchedule(Long id, List<DailySchedule> internSchedule) throws ResourceNotFoundException {
        // Find and update intern's schedule
        Intern intern = interns.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No intern found with ID: " + id));

        intern.setWeeklySchedule(internSchedule);
        saveData();
        // logs occurence of use
        logger.info("update intern schedule was used");
        return intern;
    }

    @Override
    public Pair<Boolean, String> delete(Long id) {
        boolean removed = interns.removeIf(intern -> intern.getId().equals(id));
        saveData();
        // logs occurence of use
        logger.info("delete intern was used");
        return new Pair<>(removed, removed ? "Intern deleted successfully" : "Intern not found");
    }
}