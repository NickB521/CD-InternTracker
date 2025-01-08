package com.codedifferently.CD_InternTracker.controllers;

import com.codedifferently.CD_InternTracker.models.Intern;
import com.codedifferently.CD_InternTracker.services.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/interns")
public class InternController {
    private InternService internService;

    @Autowired
    private InternController(InternService internService) {
        this.internService = internService;
    }

    @GetMapping
    public ResponseEntity<List<Intern>> getAll () {
        List<Intern> Interns = internService.getAll();
        return new ResponseEntity<>(Interns, HttpStatus.OK);
    }
}
