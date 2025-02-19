package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.TA;
import com.codedifferently.CD_InternTracker.storage.JsonDataStorage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TAServiceImpl implements TAService {
    
    private List<TA> TAs;

    public TAServiceImpl() {
        loadData();
    }

    private void loadData() {
        TAs = (List<TA>) JsonDataStorage.loadData().get("TAs");
    }

    private void saveData() {
        JsonDataStorage.saveData(null, TAs);
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
        saveData();
        return existingTA;
    }

    @Override
    public void delete(Long id) {
        TAs.removeIf(ta -> ta.getId().equals(id));
        saveData();
    }
}