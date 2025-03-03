package com.codedifferently.CD_InternTracker.storage;

import com.codedifferently.CD_InternTracker.models.Intern;
import com.codedifferently.CD_InternTracker.models.TA;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JsonDataStorage {
    private static final String FILE_PATH = "storage/JsonDataStorage.json"; 
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        ensureStorageExists();
    }

    private static void ensureStorageExists() {
        try {
            File file = new File(FILE_PATH);
            File folder = file.getParentFile();

            if (!folder.exists()) {
                folder.mkdirs();
            }

            if (!file.exists()) {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, getDefaultData());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, List<?>> loadData() {
        try {
            File file = new File(FILE_PATH);
            return objectMapper.readValue(file, new TypeReference<Map<String, List<?>>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return getDefaultData();
        }
    }

    public static void saveData(List<Intern> interns, List<TA> tas) {
 API-endpoint-to-fetch-TA-Weekly-schedules
        Map<String, List<?>> data = loadData();

        // Check that lists are not null
        data.put("interns", interns != null ? interns : new ArrayList<>());
        data.put("TAs", tas != null ? tas : new ArrayList<>());


 dev
        try {
            Map<String, List<?>> data = Map.of("interns", interns, "TAs", tas);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<?>> getDefaultData() {
        Map<String, List<?>> defaultData = new HashMap<>();
        defaultData.put("interns", new ArrayList<>());
        defaultData.put("TAs", new ArrayList<>());
        return defaultData;
    }
}