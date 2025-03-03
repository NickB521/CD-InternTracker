package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.TA;
import com.codedifferently.CD_InternTracker.models.WeeklySchedule;
import com.codedifferently.CD_InternTracker.storage.JsonDataStorage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class TAServiceImpl implements TAService {

    private List<TA> TAs;

    public TAServiceImpl() {
        loadData();
    }

    private void loadData() {
        Map<String, List<?>> data = JsonDataStorage.loadData();
        this.TAs = (List<TA>) data.get("TAs");
    }

    private void saveData() {
        JsonDataStorage.saveData(null, this.TAs);
    }

    @Override
    public TA create(TA ta) throws ResourceCreationException {
        Optional<TA> optional = TAs.stream()
                .filter(existingTA -> existingTA.getEmail().equals(ta.getEmail()))
                .findFirst();
        if (optional.isPresent()) {
            throw new ResourceCreationException("User with email exists: " + ta.getEmail());
        }
        TAs.add(ta);
        saveData();
        return ta;
    }

    @Override
    public TA getById(Long id) throws ResourceNotFoundException {
        return TAs.stream()
                .filter(ta -> ta.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No user with id: " + id));
    }

    @Override
    public TA getByEmail(String email) throws ResourceNotFoundException {
        return TAs.stream()
                .filter(ta -> ta.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No user with email: " + email));
    }

    @Override
    public List<TA> getAll() {
        return TAs;
    }

    @Override
    public TA update(Long id, TA TADetail) {
        TA existingTA = getById(id);
        existingTA.setPassword(TADetail.getPassword());
        existingTA.setEmail(TADetail.getEmail());
        existingTA.setPhoneNumber(TADetail.getPhoneNumber());
        existingTA.setName(TADetail.getName());
        existingTA.setAdmin(TADetail.isAdmin());
        existingTA.setTA(TADetail.isTA());
        existingTA.setWeeklySchedule(TADetail.getWeeklySchedule());  // Update weekly schedule
        saveData();
        return existingTA;
    }

    @Override
    public void delete(Long id) {
        TAs.removeIf(ta -> ta.getId().equals(id));
        saveData();
    }

    // New method to retrieve Weekly Schedule for a specific TA
    @Override
    public List<WeeklySchedule> getWeeklyScheduleByTAId(Long id) throws ResourceNotFoundException {
        TA ta = getById(id);
        if (ta != null && ta.getWeeklySchedule() != null) {
            return ta.getWeeklySchedule();
        } else {
            throw new ResourceNotFoundException("No weekly schedule found for TA with ID: " + id);
        }
    }

    // New method to add a Weekly Schedule to a specific TA
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

    // New method to remove a specific Weekly Schedule from a TA
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