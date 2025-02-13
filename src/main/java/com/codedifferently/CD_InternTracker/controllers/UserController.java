package com.codedifferently.CD_InternTracker.controllers;

import com.codedifferently.CD_InternTracker.models.TA;
import com.codedifferently.CD_InternTracker.services.TAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private TAService TAService;

    @Autowired
    private UserController(TAService TAService) {
        this.TAService = TAService;
    }

    @GetMapping
    public ResponseEntity<List<TA>> getAll () {
        List<TA> TAS = TAService.getAll();
        return new ResponseEntity<>(TAS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TA> create(@RequestBody TA TA){
        TA = TAService.create(TA);
        return new ResponseEntity<>(TA, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TA> getById(@PathVariable("id") Long id){
       TA TA = TAService.getById(id);
        return new ResponseEntity<>(TA, HttpStatus.OK);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<TA> getByEmail(@PathVariable String email){
        TA TA = TAService.getByEmail(email);
        return new ResponseEntity<>(TA, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TA> update(@PathVariable("id") Long id, @RequestBody TA TADetail){
        TADetail = TAService.update(id, TADetail);
        return new ResponseEntity<>(TADetail, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        TAService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
