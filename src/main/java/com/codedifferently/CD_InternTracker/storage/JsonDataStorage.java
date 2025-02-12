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
}