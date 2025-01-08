package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.models.Intern;

import java.util.List;

public interface InternService {
    Intern create(Intern Intern);
    List<Intern> getAll();
    List<Intern> getByLevel(String level);
    Intern getById(Long id);
    Intern update(Long id, Intern Intern);
    void delete(Long id);
}
