package com.codedifferently.CD_InternTracker.controllers;

import com.codedifferently.CD_InternTracker.models.TA;
import com.codedifferently.CD_InternTracker.models.WeeklySchedule;
import com.codedifferently.CD_InternTracker.services.TAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private TAService TAService;

    @Autowired
    public UserController(TAService TAService) {
        this.TAService = TAService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TA>> getAll() {
        List<TA> TAS = TAService.getAll();
        return new ResponseEntity<>(TAS, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TA> create(@RequestBody TA TA) {
        TA = TAService.create(TA);
        return new ResponseEntity<>(TA, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TA> getById(@PathVariable("id") Long id) {
        TA TA = TAService.getById(id);
        return new ResponseEntity<>(TA, HttpStatus.OK);
    }

    @GetMapping("email/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TA> getByEmail(@PathVariable String email) {
        TA TA = TAService.getByEmail(email);
        return new ResponseEntity<>(TA, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TA> update(@PathVariable("id") Long id, @RequestBody TA TADetail) {
        TADetail = TAService.update(id, TADetail);
        return new ResponseEntity<>(TADetail, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        TAService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // New endpoint to get Weekly Schedule for a specific TA
    @GetMapping("{id}/weeklySchedule")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<WeeklySchedule>> getWeeklySchedule(@PathVariable("id") Long id) {
        // Assuming the TAService has a method to retrieve the weekly schedule
        List<WeeklySchedule> schedule = TAService.getWeeklyScheduleByTAId(id);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }
}