package com.codedifferently.CD_InternTracker.services;
import com.codedifferently.CD_InternTracker.exceptions.ResourceCreationException;
import com.codedifferently.CD_InternTracker.exceptions.ResourceNotFoundException;
import com.codedifferently.CD_InternTracker.models.TA;
import com.codedifferently.CD_InternTracker.repos.TARepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
@SpringBootTest
public class TAServiceImplTests {

    @Mock
    private TARepo taRepo;

    @InjectMocks
    private TAServiceImpl taService;

    private TA ta;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Using the parameterized constructor of TA
        ta = new TA(1L, "test@example.com", "password123", "123-456-7890", "John Doe", false, true);
    }

    // Test create method
    @Test
    public void testCreateTA_Success() throws ResourceCreationException {
        when(taRepo.findByEmail(ta.getEmail())).thenReturn(Optional.empty());
        when(taRepo.save(any(TA.class))).thenReturn(ta);

        TA createdTA = taService.create(ta);

        assertNotNull(createdTA);
        assertEquals("test@example.com", createdTA.getEmail());
        verify(taRepo, times(1)).findByEmail(ta.getEmail());
        verify(taRepo, times(1)).save(ta);
    }

    // Test create method when email already exists
    @Test
    public void testCreateTA_EmailExists() {
        when(taRepo.findByEmail(ta.getEmail())).thenReturn(Optional.of(ta));

        assertThrows(ResourceCreationException.class, () -> taService.create(ta));
    }

    // Test getById method
    @Test
    public void testGetById_Success() throws ResourceNotFoundException {
        when(taRepo.findById(1L)).thenReturn(Optional.of(ta));

        TA fetchedTA = taService.getById(1L);

        assertNotNull(fetchedTA);
        assertEquals(1L, fetchedTA.getId());
        assertEquals("test@example.com", fetchedTA.getEmail());
        verify(taRepo, times(1)).findById(1L);
    }

    // Test getById method when not found
    @Test
    public void testGetById_NotFound() {
        when(taRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> taService.getById(1L));
    }

    // Test getByEmail method
    @Test
    public void testGetByEmail_Success() throws ResourceNotFoundException {
        when(taRepo.findByEmail(ta.getEmail())).thenReturn(Optional.of(ta));

        TA fetchedTA = taService.getByEmail(ta.getEmail());

        assertNotNull(fetchedTA);
        assertEquals("test@example.com", fetchedTA.getEmail());
        verify(taRepo, times(1)).findByEmail(ta.getEmail());
    }

    // Test getByEmail method when not found
    @Test
    public void testGetByEmail_NotFound() {
        when(taRepo.findByEmail(ta.getEmail())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> taService.getByEmail(ta.getEmail()));
    }

    // Test update method
    @Test
    public void testUpdateTA_Success() {
        TA updatedTA = new TA(1L, "newemail@example.com", "newpassword123", "987-654-3210", "John Updated", true, false);

        when(taRepo.findById(1L)).thenReturn(Optional.of(ta));
        when(taRepo.save(any(TA.class))).thenReturn(updatedTA);

        TA result = taService.update(1L, updatedTA);

        assertNotNull(result);
        assertEquals("newemail@example.com", result.getEmail());
        assertEquals("987-654-3210", result.getPhoneNumber());
        verify(taRepo, times(1)).save(updatedTA);
    }

    // Test delete method
    @Test
    public void testDeleteTA_Success() {
        when(taRepo.findById(1L)).thenReturn(Optional.of(ta));

        taService.delete(1L);

        verify(taRepo, times(1)).delete(ta);
    }
}