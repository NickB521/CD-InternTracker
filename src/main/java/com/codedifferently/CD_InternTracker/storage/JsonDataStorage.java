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

}