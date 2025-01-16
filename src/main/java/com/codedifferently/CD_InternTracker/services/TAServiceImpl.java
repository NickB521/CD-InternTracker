package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.TA;
import com.codedifferently.CD_InternTracker.repos.TARepo;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class TAServiceImpl implements TAService {
private TARepo TARepo;

    public TAServiceImpl(TARepo TARepo) {
        this.TARepo = TARepo;
    }

    @Override
    public TA create(TA TA) throws ResourceCreationException {
        Optional<TA> optional = TARepo.findByEmail(TA.getEmail());
        if(optional.isPresent()){
            throw new ResourceCreationException("User with email exists: " + TA.getEmail());
        }
       TA = TARepo.save(TA);
        return TA;
    }

    @Override
    public TA getById(Long id) throws ResourceNotFoundException {
        TA TA = TARepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No user with id: " + id));
        return TA;
    }

    @Override
    public TA getByEmail(String email) throws ResourceNotFoundException {
       TA TA = TARepo.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("No user with email: " + email));
        return TA;
    }

    @Override
    public List<TA> getAll() {
        return TARepo.findAll();
    }

    @Override
    public TA update(Long id, TA TADetail) {
        TA TA = getById(id);
        TA.setPassword(TADetail.getPassword());
        TA.setEmail(TADetail.getEmail());
        TA.setPhoneNumber(TADetail.getPhoneNumber());
        TA.setName(TADetail.getName());
        TA.setAdmin(TADetail.isAdmin());
        TA.setTA(TADetail.isTA());
        TA = TARepo.save(TA);
        return TA;


    }

    @Override
    public void delete(Long id) {
        TA TA = getById(id);
        TARepo.delete(TA);
    }


}
