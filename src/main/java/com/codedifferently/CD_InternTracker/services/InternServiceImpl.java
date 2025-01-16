package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.DailySchedule;
import com.codedifferently.CD_InternTracker.models.Intern;
import com.codedifferently.CD_InternTracker.repos.InternRepository;
import com.opencsv.CSVReader;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.Exception;
import java.util.concurrent.ExecutionException;

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
    public List<Intern> createByCSV(MultipartFile csvFile) throws Exception {
        ArrayList<Intern> interns = new ArrayList<>();
        try  {
            CSVReader csvReader = new CSVReader(new InputStreamReader(csvFile.getInputStream()));
            List<String[]> rows = csvReader.readAll();
            rows.remove(0);


        }
        catch (Exception e) {
            return interns;
        };
        return null;
    }

    @Override
    public List<Intern> getAll() {
        return internRepository.findAll();
    }

    @Override
    public List<Intern> getByLevel(String level) {
        return internRepository.findByLevel(level);
    }

    @Override
    public Pair<Boolean, Intern> getById(Long id) {
        Optional<Intern> optional = internRepository.findById(id);
        if (optional.isPresent()) {
            return new Pair<Boolean, Intern>(true,optional.get());
        }
        else {
            return new Pair<Boolean, Intern>(false, null);
        }
    }

    @Override
    public Intern update(Long id, Intern oldIntern) {
        Intern updatedIntern = getById(id).b;
        updatedIntern.setName(oldIntern.getName());
        updatedIntern.setEmail(oldIntern.getEmail());
        updatedIntern.setInternNotes(oldIntern.getInternNotes());
        updatedIntern.setAttendance(oldIntern.getAttendance());
        updatedIntern.setWeeklySchedule(oldIntern.getWeeklySchedule());
        updatedIntern = internRepository.save(updatedIntern);
        return updatedIntern;
    }

    @Override
    public Intern updateInternSchedule(Long id, List<DailySchedule> internSchedule) throws ResourceNotFoundException {

        //this doesn't do anything yet
        return null;
    }


   @Override
   public Intern updateInternSchedule(Long id, List<DailySchedule> internSchedule) throws ResourceNotFoundException {
       try {
           if (!document.exists()) {
               throw new ResourceNotFoundException("User not found with uid: " + id);
           }
          ApiFuture<WriteResult> updateFuture = docRef.set(userDetails);
           updateFuture.get();
          return userDetails;
       } catch (InterruptedException | ExecutionException e) {
            throw new ResourceNotFoundException("Failed to update user");
        }
    }



    @Override
    public Pair<Boolean, String> delete(Long id) {
        Optional<Intern> optional = internRepository.findById(id);
        if (optional.isPresent()) {
            internRepository.deleteById(id);
            return new Pair<Boolean, String>(true,"Object with id: " + id + " successfully deleted");
        }
        else {
            return new Pair<Boolean, String>(false,"Object with id: " + id + " not found");
        }
    }
}
