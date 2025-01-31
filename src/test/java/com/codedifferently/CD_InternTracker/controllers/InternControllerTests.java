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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

    @Test
    void testGetById() throws Exception {
        Long id = 1L;
        Intern intern = new Intern();
        when(internService.getById(id)).thenReturn(new Pair<>(true, intern));

        mockMvc.perform(get("/api/intern/id").param("id", id.toString()))
                .andExpect(status().isOk());

        verify(internService, times(1)).getById(id);
    }

    @Test
    void testGetById_NotFound() throws Exception {
        Long id = 1L;
        when(internService.getById(id)).thenReturn(new Pair<>(false, null));

        mockMvc.perform(get("/api/intern/id").param("id", id.toString()))
                .andExpect(status().isNotFound());

        verify(internService, times(1)).getById(id);
    }

    @Test
    void testUpdate() throws Exception {
        Long id = 1L;
        Intern internDetail = new Intern();
        when(internService.update(eq(id), any(Intern.class))).thenReturn(internDetail);

        mockMvc.perform(put("/api/intern/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isAccepted());

        verify(internService, times(1)).update(eq(id), any(Intern.class));
    }

    @Test
    void testUpdateInternSchedule() throws Exception {
        Long id = 1L;
        List<DailySchedule> schedule = Collections.emptyList();
        Intern updatedIntern = new Intern();
        when(internService.updateInternSchedule(id, schedule)).thenReturn(updatedIntern);

        mockMvc.perform(put("/api/intern/schedule/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[]"))
                .andExpect(status().isOk());

        verify(internService, times(1)).updateInternSchedule(id, schedule);
    }

    @Test
    void testCreate() throws Exception {
        Intern intern = new Intern();
        when(internService.create(any(Intern.class))).thenReturn(intern);

        mockMvc.perform(post("/api/intern")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());

        verify(internService, times(1)).create(any(Intern.class));
    }

    @Test
    void testCreateByCSV() throws Exception {
        MockMultipartFile csvFile = new MockMultipartFile("csvFile", "test.csv", "text/csv", "data".getBytes());
        List<Intern> interns = Arrays.asList(new Intern(), new Intern());
        when(internService.createByCSV(any())).thenReturn(interns);

        mockMvc.perform(multipart("/api/intern/csv").file(csvFile))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(interns.size()));

        verify(internService, times(1)).createByCSV(any());
    }

    @Test
    void testDelete() throws Exception {
        Long id = 1L;
        when(internService.delete(id)).thenReturn(new Pair<>(true, "Deleted successfully"));

        mockMvc.perform(delete("/api/intern/delete").param("id", id.toString()))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Deleted successfully"));

        verify(internService, times(1)).delete(id);
    }

    @Test
    void testDelete_NotFound() throws Exception {
        Long id = 1L;
        when(internService.delete(id)).thenReturn(new Pair<>(false, "Intern not found"));

        mockMvc.perform(delete("/api/intern/delete").param("id", id.toString()))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Intern not found"));

        verify(internService, times(1)).delete(id);
    }
}