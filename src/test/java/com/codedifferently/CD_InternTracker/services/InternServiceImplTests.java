package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.Intern;
import com.codedifferently.CD_InternTracker.repos.InternRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InternServiceImplTest {
    @Mock
    private InternRepository internRepository;

    @InjectMocks
    private InternServiceImpl internService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreate_InternDoesNotExist() throws ResourceCreationException {
        Intern intern = new Intern();
        intern.setEmail("test@example.com");

        when(internRepository.findByEmail(intern.getEmail())).thenReturn(Optional.empty());
        when(internRepository.save(intern)).thenReturn(intern);

        Intern createdIntern = internService.create(intern);

        assertNotNull(createdIntern);
        verify(internRepository, times(1)).save(intern);
    }
    @Test
    void testCreate_InternAlreadyExists() {
        Intern intern = new Intern();
        intern.setEmail("test@example.com");

        when(internRepository.findByEmail(intern.getEmail())).thenReturn(Optional.of(intern));

        assertThrows(ResourceCreationException.class, () -> internService.create(intern));
        verify(internRepository, never()).save(any());
    }
}