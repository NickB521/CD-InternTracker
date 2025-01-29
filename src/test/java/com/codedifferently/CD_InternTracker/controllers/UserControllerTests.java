package com.codedifferently.CD_InternTracker.controllers;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.models.Intern;
import com.codedifferently.CD_InternTracker.models.TA;
import com.codedifferently.CD_InternTracker.repos.TARepo;
import com.codedifferently.CD_InternTracker.services.TAServiceImpl;
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
//test ensures create function works correctly on normal circumstances
    void testCreate_TADoesNotExist() throws ResourceCreationException {
        TA TA = new TA();
        TA.setEmail("test@example.com");

        when(TARepository.findByEmail(TA.getEmail())).thenReturn(Optional.empty());
        when(TARepository.save(TA)).thenReturn(TA);

       TA createdTA = TAService.create(TA);

        assertNotNull(createdTA);
        verify(TARepository, times(1)).save(TA);
    }
    @Test
    void testCreate_TAAlreadyExists() {
        TA TA = new TA();
        TA.setEmail("test@example.com");

        when(TARepository.findByEmail(TA.getEmail())).thenReturn(Optional.of(TA));

        assertThrows(ResourceCreationException.class, () -> TAService.create(TA));
        verify(TARepository, never()).save(any());
    }
    @Test
    void testGetAll() {
        TA<TA> TAList = new ArrayList<TA>();
        TAList.add(new TA());
        TAList.add(new TA());

        when(TARepository.findAll()).thenReturn(TAList);

        List<TA> result = TAService.getAll();

        assertEquals(2, result.size());
        verify(TARepository, times(1)).findAll();
    }


    //create tests for: Get by id, get by email, getall, update, delete
}
