package com.codedifferently.CD_InternTracker.services;

import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
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
    @Test
    void testGetAll() {
        List<Intern> internList = new ArrayList<>();
        internList.add(new Intern());
        internList.add(new Intern());

        when(internRepository.findAll()).thenReturn(internList);

        List<Intern> result = internService.getAll();

        assertEquals(2, result.size());
        verify(internRepository, times(1)).findAll();
    }
    @Test
    void testGetById_Found() {
        Intern intern = new Intern();
        intern.setId(1L);

        when(internRepository.findById(1L)).thenReturn(Optional.of(intern));

        var result = internService.getById(1L);

        assertTrue(result.a);
        assertEquals(intern, result.b);
        verify(internRepository, times(1)).findById(1L);
    }
    @Test
    void testGetById_NotFound() {
        when(internRepository.findById(1L)).thenReturn(Optional.empty());

        var result = internService.getById(1L);

        assertFalse(result.a);
        assertNull(result.b);
        verify(internRepository, times(1)).findById(1L);
    }
    @Test
    void testUpdate() {
        Intern existingIntern = new Intern();
        existingIntern.setId(1L);

        Intern updatedIntern = new Intern();
        updatedIntern.setName("Updated Name");

        when(internRepository.findById(1L)).thenReturn(Optional.of(existingIntern));
        when(internRepository.save(existingIntern)).thenReturn(existingIntern);

        Intern result = internService.update(1L, updatedIntern);

        assertEquals("Updated Name", result.getName());
        verify(internRepository, times(1)).save(existingIntern);
    }
    @Test
    void testDelete_Found() {
        Intern intern = new Intern();
        intern.setId(1L);

        when(internRepository.findById(1L)).thenReturn(Optional.of(intern));
        doNothing().when(internRepository).deleteById(1L);

        var result = internService.delete(1L);

        assertTrue(result.a);
        assertEquals("Object with id: 1 successfully deleted", result.b);
        verify(internRepository, times(1)).deleteById(1L);
    }
    @Test
    void testDelete_NotFound() {
        when(internRepository.findById(1L)).thenReturn(Optional.empty());

        var result = internService.delete(1L);

        assertFalse(result.a);
        assertEquals("Object with id: 1 not found", result.b);
        verify(internRepository, never()).deleteById(1L);
    }
}