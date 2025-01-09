package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.models.Intern;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;

public interface InternService {
    Intern create(Intern intern);
    List<Intern> getAll();
    List<Intern> getByLevel(String level);
    Intern getById(Long id);
    Intern update(Long id, Intern intern);
    Pair<Boolean, String> delete(Long id);
}
