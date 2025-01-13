//package com.codedifferently.CD_InternTracker.controllers;
//
//import com.codedifferently.CD_InternTracker.models.User;
//import com.codedifferently.CD_InternTracker.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//    private UserService userService;
//
//    @Autowired
//    private UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<User>> getAll () {
//        List<User> Users = userService.getAll();
//        return new ResponseEntity<>(Users, HttpStatus.OK);
//    }
//}
