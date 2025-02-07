package com.codedifferently.CD_InternTracker.controllers;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
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
        List<TA> TAList = new ArrayList<>();
        TAList.add(new TA());
        TAList.add(new TA());

        when(TARepository.findAll()).thenReturn(TAList);

        List<TA> result = TAService.getAll();

        assertEquals(2, result.size());
        verify(TARepository, times(1)).findAll();
    }

    @Test
    void testGetById_Found() {
        TA TA = new TA();
        TA.setId(1L);

        when(TARepository.findById(1L)).thenReturn(Optional.of(TA));

        var result = TAService.getById(1L);

        assertEquals(TA, result);
        verify(TARepository, times(1)).findById(1L);
    }


    @Test
    void testGetById_NotFound() {
        when(TARepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> TAService.getById(1L), "Expected getById() to throw, but it didn't!");
        assertThrows(ResourceNotFoundException.class, () -> TAService.getById(99L), "Expected getById() to throw, but it didn't!");
        verify(TARepository, times(1)).findById(1L);
        verify(TARepository, times(1)).findById(99L);
    }

    @Test
    void testUpdate() {
        TA existingTA = new TA();
        existingTA.setId(1L);

        TA updatedTA = new TA();
        updatedTA.setName("Updated Name");
        updatedTA.setPassword("Pass2");
        updatedTA.setEmail("email@2.com");
        updatedTA.setPhoneNumber("302-111-1111");

        when(TARepository.findById(1L)).thenReturn(Optional.of(existingTA));
        when(TARepository.save(existingTA)).thenReturn(existingTA);

        TA result = TAService.update(1L, updatedTA);

        assertEquals("Updated Name", result.getName());
        verify(TARepository, times(1)).save(existingTA);
    }

    @Test
    void testDelete_Found() {
        TA TA1 = new TA();
        TA1.setId(1L);


        when(TARepository.findById(1L)).thenReturn(Optional.of(TA1));
        doNothing().when(TARepository).deleteById(1L);

        TAService.delete(1L);
        assertThrows(ResourceNotFoundException.class, () -> TAService.getById(1L), "Expected getById() to throw, but it didn't!");
        verify(TARepository, times(1)).findById(1L);
    }

    @Test
    void testDelete_NotFound() {
        when(TARepository.findById(1L)).thenReturn(Optional.empty());


        verify(TARepository, never()).deleteById(1L);
    }


    @Test
    void testGetByEmail_Found() {
        // Arrange
        TA TA = new TA();
        TA.setEmail("test@example.com");

        // Mock the repository to return the TA object when findByEmail is called
        when(TARepository.findByEmail(TA.getEmail())).thenReturn(Optional.of(TA));

        // Act
        TA result = TAService.getByEmail(TA.getEmail());

        // Assert
        assertEquals(TA, result);
        verify(TARepository, times(1)).findByEmail(TA.getEmail());
    }

    @Test
    void testGetByEmail_NotFound() {
        // Arrange
        String email = "nonexistent@example.com";

        // Mock the repository to return an empty Optional for a non-existing email
        when(TARepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> TAService.getByEmail(email),
                "Expected getByEmail() to throw, but it didn't!");
        verify(TARepository, times(1)).findByEmail(email);
    }
}