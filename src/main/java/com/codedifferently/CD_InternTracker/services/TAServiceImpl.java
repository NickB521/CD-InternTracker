package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.TA;
import com.codedifferently.CD_InternTracker.models.WeeklySchedule;
import com.codedifferently.CD_InternTracker.storage.JsonDataStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.ArrayList;


import java.util.List;
import java.util.Optional;



@Service
public class TAServiceImpl implements TAService {

    private List<TA> TAs;
    private static final Logger logger = LoggerFactory.getLogger(TAServiceImpl.class);

    public TAServiceImpl() {
        loadData();
    }

    private void loadData() {
        TAs = (List<TA>) JsonDataStorage.loadTAs();
        logger.info("TA data loaded successfully.");
    }

    private void saveData() {
        JsonDataStorage.saveTAs(this.TAs);
        logger.info("TA data saved successfully.");
    }

    @Override
    public TA create(TA ta) throws ResourceCreationException {
        logger.debug("Creating TA with email: {}", ta.getEmail());

        Optional<TA> optional = TAs.stream()
                .filter(existingTA -> existingTA.getEmail().equals(ta.getEmail()))
                .findFirst();
        if (optional.isPresent()) {
            logger.error("TA with email {} already exists.", ta.getEmail());
            throw new ResourceCreationException("User with email exists: " + ta.getEmail());
        }
        TAs.add(ta);
        saveData();
        logger.info("TA created successfully with email: {}", ta.getEmail());
        return ta;
    }

    @Override
    public TA getById(Long id) throws ResourceNotFoundException {
        logger.info("Fetching TA by ID: {}", id);
        return TAs.stream()
                .filter(ta -> ta.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No user with id: " + id));
    }

    @Override
    public TA getByEmail(String email) throws ResourceNotFoundException {
        logger.info("Fetching TA by email: {}", email);
        return TAs.stream()
                .filter(ta -> ta.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No user with email: " + email));
    }

    @Override
    public List<TA> getAll() {
        logger.info("Fetching all TAs.");
        return TAs;
    }

    @Override
    public TA update(Long id, TA TADetail) {
        logger.debug("Updating TA with ID: {}", id);

        TA existingTA = getById(id);
        existingTA.setPassword(TADetail.getPassword());
        existingTA.setEmail(TADetail.getEmail());
        existingTA.setPhoneNumber(TADetail.getPhoneNumber());
        existingTA.setName(TADetail.getName());
        existingTA.setAdmin(TADetail.isAdmin());
        existingTA.setTA(TADetail.isTA());
        existingTA.setWeeklySchedule(TADetail.getWeeklySchedule());  // Update weekly schedule
        saveData();
        logger.info("TA with ID {} updated successfully.", id);
        return existingTA;
    }

    @Override
    public void delete(Long id) {
        logger.debug("Deleting TA with ID: {}", id);
        TAs.removeIf(ta -> ta.getId().equals(id));
        saveData();
        logger.info("TA with ID {} deleted successfully.", id);
    }

    @Override
    public List<WeeklySchedule> getWeeklyScheduleByTAId(Long id) throws ResourceNotFoundException {
        TA ta = getById(id);
        if (ta != null && ta.getWeeklySchedule() != null) {
            return ta.getWeeklySchedule();
        } else {
            throw new ResourceNotFoundException("No weekly schedule found for TA with ID: " + id);
        }
    }

    @Override
    public void addWeeklySchedule(Long id, WeeklySchedule weeklySchedule) throws ResourceNotFoundException {
        TA ta = getById(id);
        if (ta != null) {
            if (ta.getWeeklySchedule() == null) {
                ta.setWeeklySchedule(new ArrayList<>());
            }
            ta.getWeeklySchedule().add(weeklySchedule);
            saveData();
        } else {
            throw new ResourceNotFoundException("TA not found with ID: " + id);
        }
    }

    @Override
    public void removeWeeklySchedule(Long id, Long scheduleId) throws ResourceNotFoundException {
        TA ta = getById(id);
        if (ta != null && ta.getWeeklySchedule() != null) {
            ta.getWeeklySchedule().removeIf(schedule -> schedule.getId().equals(scheduleId));
            saveData();
        } else {
            throw new ResourceNotFoundException("No weekly schedule found for TA with ID: " + id);
        }
    }
}