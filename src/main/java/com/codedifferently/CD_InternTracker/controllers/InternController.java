package com.codedifferently.CD_InternTracker.controllers;

import com.codedifferently.CD_InternTracker.models.Intern;
import com.codedifferently.CD_InternTracker.services.InternService;
import org.antlr.v4.runtime.misc.Pair;
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
    @GetMapping("id")
    public ResponseEntity<Intern> getById (@RequestParam("id") Long id) {
        Pair<Boolean, Intern> result = internService.getById(id);
        if (result.a) {
            return new ResponseEntity<>(result.b, HttpStatus.OK);

        }
        else {
            return new ResponseEntity<>(result.b, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Intern> create (@RequestBody Intern intern) {
        Intern saved = internService.create(intern);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    @DeleteMapping("delete")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        Pair<Boolean, String> result = internService.delete(id);
        if (result.a) {
            return new ResponseEntity<>(result.b, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(result.b, HttpStatus.NOT_FOUND);

        }
    }
}
