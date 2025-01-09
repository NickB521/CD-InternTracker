package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.models.Intern;
import com.codedifferently.CD_InternTracker.repos.InternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternServiceImpl implements InternService{

    private InternRepository internRepository;

    @Autowired
    public InternServiceImpl(InternRepository internRepository) {
        this.internRepository = internRepository;
    }
    @Override
    public Intern create(Intern intern) throws ResourceCreationException {

        Optional<Intern> optional = internRepository.findByEmail(intern.getEmail());
        if (optional.isPresent()) {
            throw new ResourceCreationException("Intern with email: " + intern.getEmail() + " already exists.");
        }
        internRepository.save(intern);
        return intern;
    }

    @Override
    public List<Intern> getAll() {
        return internRepository.findAll();
    }

    @Override
    public List<Intern> getByLevel(String level) {
        return null;
    }

    @Override
    public Intern getById(Long id) {
        return null;
    }

    @Override
    public Intern update(Long id, Intern intern) {
        return null;
    }

    @Override
    public String delete(Long id) {
        Optional<Intern> optional = internRepository.findById(id);
        if (optional.isPresent()) {
            internRepository.deleteById(id);
            return ("Object with id: " + id + " successfully deleted");
        }
        else {
            return ("Object with id: " + id + " not found");
        }
    }
}
