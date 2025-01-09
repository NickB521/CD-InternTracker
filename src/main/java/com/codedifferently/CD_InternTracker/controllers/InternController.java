package com.codedifferently.CD_InternTracker.controllers;

import com.codedifferently.CD_InternTracker.models.Intern;
import com.codedifferently.CD_InternTracker.services.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<Intern> interns = internService.getAll();
        return new ResponseEntity<>(interns, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Intern> create (@RequestBody Intern intern) {
        Intern saved = internService.create(intern);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    @DeleteMapping("delete")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        String message = internService.delete(id);
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }
}
