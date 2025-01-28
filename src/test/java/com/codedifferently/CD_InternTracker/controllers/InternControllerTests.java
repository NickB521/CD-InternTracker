package com.codedifferently.CD_InternTracker.controllers;

import com.codedifferently.CD_InternTracker.models.DailySchedule;
import com.codedifferently.CD_InternTracker.models.Intern;
import com.codedifferently.CD_InternTracker.services.InternService;
import org.antlr.v4.runtime.misc.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class InternControllerTests {

    @Mock
    private InternService internService;

    @InjectMocks
    private InternController internController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(internController).build();
    }

    @Test
    void testGetAll() throws Exception {
        List<Intern> interns = Arrays.asList(new Intern(), new Intern());
        when(internService.getAll()).thenReturn(interns);

        mockMvc.perform(get("/api/intern"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(interns.size()));

        verify(internService, times(1)).getAll();
    }
}