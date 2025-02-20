package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.DailySchedule;
import com.codedifferently.CD_InternTracker.models.Intern;
import com.codedifferently.CD_InternTracker.storage.JsonDataStorage;
import com.codedifferently.CD_InternTracker.utils.ValidationUtils;
import com.opencsv.CSVReader;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InternServiceImpl implements InternService {

    private List<Intern> interns;

    public InternServiceImpl() {
        loadData();
    }

    private void loadData() {
        Map<String, List<?>> data = JsonDataStorage.loadData();
        this.interns = (List<Intern>) data.get("interns");
    }

    private void saveData() {
        JsonDataStorage.saveData(this.interns, null);
    }

    @Override
    public Intern create(Intern intern) {
        if (!ValidationUtils.areRequiredFieldsValid(intern.getEmail(), intern.getName(), intern.getId())) {
            throw new IllegalArgumentException("Invalid Intern data: Please check the email, name, or ID fields.");
        }

        Optional<Intern> existingIntern = interns.stream()
                .filter(i -> i.getEmail().equals(intern.getEmail()))
                .findFirst();

        if (existingIntern.isPresent()) {
            throw new IllegalArgumentException("Intern with email already exists: " + intern.getEmail());
        }

        interns.add(intern);
        saveData();
        return intern;
    }

    @Override
    public List<Intern> createByCSV(MultipartFile csvFile) throws Exception {
        List<Intern> parsedInterns = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(csvFile.getInputStream()))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length < 3) continue;

                String name = nextLine[0];
                String email = nextLine[1];
                Long id = Long.parseLong(nextLine[2]);

                Intern intern = new Intern(name, email, id);
                parsedInterns.add(intern);
            }
        } catch (IOException e) {
            throw new IOException("Error parsing CSV file", e);
        }

        for (Intern intern : parsedInterns) {
            if (!ValidationUtils.areRequiredFieldsValid(intern.getEmail(), intern.getName(), intern.getId())) {
                throw new IllegalArgumentException("Invalid Intern data in CSV.");
            }
        }

        interns.addAll(parsedInterns);
        saveData();
        return parsedInterns;
    }

    @Override
    public List<Intern> getAll() {
        return interns;
    }

    @Override
    public List<Intern> getByLevel(String level) {
        // Assuming Intern class has a 'level' field
        List<Intern> filteredInterns = new ArrayList<>();
        for (Intern intern : interns) {
            if (intern.getLevel().equalsIgnoreCase(level)) {
                filteredInterns.add(intern);
            }
        }
        return filteredInterns;
    }

    @Override
    public Pair<Boolean, Intern> getById(Long id) {
        Intern intern = interns.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
        return new Pair<>(intern != null, intern);
    }

    @Override
    public Intern update(Long id, Intern intern) {
        if (!ValidationUtils.areRequiredFieldsValid(intern.getEmail(), intern.getName(), intern.getId())) {
            throw new IllegalArgumentException("Invalid Intern data: Please check the email, name, or ID fields.");
        }

        Intern existingIntern = interns.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No intern found with ID: " + id));

        existingIntern.setName(intern.getName());
        existingIntern.setEmail(intern.getEmail());
        existingIntern.setInternNotes(intern.getInternNotes());
        existingIntern.setAttendance(intern.getAttendance());
        existingIntern.setWeeklySchedule(intern.getWeeklySchedule());

        saveData();
        return existingIntern;
    }

    @Override
    public Intern updateInternSchedule(Long id, List<DailySchedule> internSchedule) {
        Intern intern = interns.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No intern found with ID: " + id));

        intern.setWeeklySchedule(internSchedule);
        saveData();
        return intern;
    }

    @Override
    public Pair<Boolean, String> delete(Long id) {
        boolean removed = interns.removeIf(intern -> intern.getId().equals(id));
        saveData();
        return new Pair<>(removed, removed ? "Intern deleted successfully" : "Intern not found");
    }
}