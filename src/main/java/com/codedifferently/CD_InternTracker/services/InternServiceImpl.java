package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.models.Intern;
import com.codedifferently.CD_InternTracker.repos.InternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternServiceImpl implements InternService{

    private InternRepository internRepository;

    @Autowired
    public InternServiceImpl(InternRepository internRepository) {
        this.internRepository = internRepository;
    }
    @Override
    public Intern create(Intern Intern) {
        return null;
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
    public Intern update(Long id, Intern Intern) {
        return null;
    }

    @Override
    public void delete(Long id) {


    }
}
