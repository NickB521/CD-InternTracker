package com.codedifferently.CD_InternTracker.controllers;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.models.TA;
import com.codedifferently.CD_InternTracker.repos.TARepo;
import com.codedifferently.CD_InternTracker.services.TAServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserControllerTests {

    @Mock
    private TARepo TARepository;

    @InjectMocks
    private TAServiceImpl TAService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

@Test
    void testCreate_TADoesNotExist() throws ResourceCreationException {
        TA TA = new TA();
        TA.setEmail("test@example.com");

        when(TARepository.findByEmail(TA.getEmail())).thenReturn(Optional.empty());
        when(TARepository.save(TA)).thenReturn(TA);

       TA createdTA = TAService.create(TA);

        assertNotNull(createdTA);
        verify(TARepository, times(1)).save(TA);
    }
}
