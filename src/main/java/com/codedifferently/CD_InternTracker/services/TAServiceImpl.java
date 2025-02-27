package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.TA;
import com.codedifferently.CD_InternTracker.storage.JsonDataStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TAServiceImpl implements TAService {

    private List<TA> TAs;
    private static final Logger logger = LoggerFactory.getLogger(TAServiceImpl.class);

    public TAServiceImpl() {
        loadData();
    }

    private void loadData() {

        TAs = (List<TA>) JsonDataStorage.loadData().get("TAs");
        logger.info("TA data loaded successfully.");
    }

    private void saveData() {
        JsonDataStorage.saveData(null, TAs);
        logger.info("TA data saved successfully.");

        Map<String, List<?>> data = JsonDataStorage.loadData();
        this.TAs = (List<TA>) data.get("TAs");
    }

    private void saveData() {
        JsonDataStorage.saveData(null, this.TAs);

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
}