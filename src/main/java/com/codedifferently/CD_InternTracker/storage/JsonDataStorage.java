package com.codedifferently.CD_InternTracker.storage;

import com.codedifferently.CD_InternTracker.models.Intern;
import com.codedifferently.CD_InternTracker.models.TA;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class JsonDataStorage {
    private static final String FILE_PATH = "storage/JsonDataStorage.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        ensureStorageExists();
    }

    private static void ensureStorageExists() {
        File file = new File(FILE_PATH);
        File folder = file.getParentFile();

        if (!folder.exists()) {
            folder.mkdirs();
        }

        if (!file.exists()) {
            saveData(new ArrayList<>(), new ArrayList<>());
        }
    }

    public static Map<String, List<?>> loadData() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return getDefaultData();
            }

            Map<String, Object> rawData = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {});

            List<Intern> interns = objectMapper.convertValue(rawData.getOrDefault("interns", new ArrayList<>()), new TypeReference<List<Intern>>() {});
            List<TA> tas = objectMapper.convertValue(rawData.getOrDefault("TAs", new ArrayList<>()), new TypeReference<List<TA>>() {});

            Map<String, List<?>> data = new HashMap<>();
            data.put("interns", interns);
            data.put("TAs", tas);

            return data;
        } catch (IOException e) {
            throw new RuntimeException("Error loading data from storage", e);
        }
    }

    public static void saveData(List<Intern> interns, List<TA> tas) {
        Map<String, List<?>> data = loadData();

        // Check that lists are not null
        data.put("interns", interns != null ? interns : new ArrayList<>());
        data.put("TAs", tas != null ? tas : new ArrayList<>());

        try {
            objectMapper.writeValue(new File(FILE_PATH), data);
        } catch (IOException e) {
            throw new RuntimeException("Error saving data to file", e);
        }
    }

    private static Map<String, List<?>> getDefaultData() {
        Map<String, List<?>> defaultData = new HashMap<>();
        defaultData.put("interns", new ArrayList<>());
        defaultData.put("TAs", new ArrayList<>());
        return defaultData;
    }
}