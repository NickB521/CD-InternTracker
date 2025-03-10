package com.codedifferently.CD_InternTracker.storage;

import com.codedifferently.CD_InternTracker.models.Intern;
import com.codedifferently.CD_InternTracker.models.TA;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonDataStorage {

    private static final String TA_FILE_PATH = "storage/tas.json";
    private static final String INTERN_FILE_PATH = "storage/interns.json";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        ensureFileExists(TA_FILE_PATH);
        ensureFileExists(INTERN_FILE_PATH);
    }

    private static void ensureFileExists(String path) {
        try {
            File file = new File(path);
            File folder = file.getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }
            if (!file.exists()) {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, new ArrayList<>());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<TA> loadTAs() {
        try {
            return objectMapper.readValue(
                    new File(TA_FILE_PATH),
                    new TypeReference<List<TA>>() {}
            );
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public static void saveTAs(List<TA> tas) {
        if (tas == null) {
            tas = new ArrayList<>();
        }
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(TA_FILE_PATH), tas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Intern> loadInterns() {
        try {
            return objectMapper.readValue(
                    new File(INTERN_FILE_PATH),
                    new TypeReference<List<Intern>>() {}
            );
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public static void saveInterns(List<Intern> interns) {
        if (interns == null) {
            interns = new ArrayList<>();
        }
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(INTERN_FILE_PATH), interns);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
