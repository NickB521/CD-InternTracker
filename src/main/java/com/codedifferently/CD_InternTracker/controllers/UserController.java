package com.codedifferently.CD_InternTracker.controllers;

import com.codedifferently.CD_InternTracker.models.User;
import com.codedifferently.CD_InternTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll () {
        List<User> Users = userService.getAll();
        return new ResponseEntity<>(Users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        user = userService.create(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id){
       User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email){
        User user = userService.getByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User userDetail){
        userDetail = userService.update(id, userDetail);
        return new ResponseEntity<>(userDetail, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
