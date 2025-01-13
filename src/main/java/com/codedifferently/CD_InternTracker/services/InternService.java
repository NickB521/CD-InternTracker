package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.DailySchedule;
import com.codedifferently.CD_InternTracker.models.Intern;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;

public interface InternService {
    Intern create(Intern intern);
    List<Intern> getAll();
    List<Intern> getByLevel(String level);
    Pair<Boolean, Intern> getById(Long id);
    Intern update(Long id, Intern intern);
    Intern updateInternSchedule(Long id, List<DailySchedule> internSchedule) throws ResourceNotFoundException;
    Pair<Boolean, String> delete(Long id);
}
