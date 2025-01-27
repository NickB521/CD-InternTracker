package com.codedifferently.CD_InternTracker.controllers;

import com.codedifferently.CD_InternTracker.models.User;
import com.codedifferently.CD_InternTracker.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGetAllUsers() throws Exception {
        // Instantiate User using Lombok-generated constructor
        User user = new User("password123", "john.doe@example.com", "1234567890", "John", true, false);
        when(userService.getAll()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].password").value("password123"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[0].phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[0].isAdmin").value(true))
                .andExpect(jsonPath("$[0].isTA").value(false));
    }

    @Test
    void testCreateUser() throws Exception {
        // Instantiate User using Lombok-generated constructor
        User user = new User("password123", "jane.doe@example.com", "0987654321", "Jane", true, true);
        when(userService.create(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/user")
                        .contentType("application/json")
                        .content("{\"password\":\"password123\",\"email\":\"jane.doe@example.com\",\"phoneNumber\":\"0987654321\",\"name\":\"Jane\",\"isAdmin\":true,\"isTA\":true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.password").value("password123"))
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("0987654321"))
                .andExpect(jsonPath("$.name").value("Jane"))
                .andExpect(jsonPath("$.isAdmin").value(true))
                .andExpect(jsonPath("$.isTA").value(true));
    }

    @Test
    void testGetUserById() throws Exception {
        // Instantiate User using Lombok-generated constructor
        User user = new User("password123", "john.doe@example.com", "1234567890", "John", true, false);
        when(userService.getById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password").value("password123"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.isAdmin").value(true))
                .andExpect(jsonPath("$.isTA").value(false));
    }

    @Test
    void testUpdateUser() throws Exception {
        // Instantiate User using Lombok-generated constructor
        User existingUser = new User("password123", "john.doe@example.com", "1234567890", "John", true, false);
        User updatedUser = new User("newpassword123", "johnny.doe@example.com", "0987654321", "Johnny", true, true);
        when(userService.update(1L, updatedUser)).thenReturn(updatedUser);

        mockMvc.perform(put("/api/user/1")
                        .contentType("application/json")
                        .content("{\"password\":\"newpassword123\",\"email\":\"johnny.doe@example.com\",\"phoneNumber\":\"0987654321\",\"name\":\"Johnny\",\"isAdmin\":true,\"isTA\":true}"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.password").value("newpassword123"))
                .andExpect(jsonPath("$.email").value("johnny.doe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("0987654321"))
                .andExpect(jsonPath("$.name").value("Johnny"))
                .andExpect(jsonPath("$.isAdmin").value(true))
                .andExpect(jsonPath("$.isTA").value(true));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).delete(1L);

        mockMvc.perform(delete("/api/user/1"))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).delete(1L);
    }
}
