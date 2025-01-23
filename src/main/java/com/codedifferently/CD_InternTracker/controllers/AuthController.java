package com.codedifferently.CD_InternTracker.controllers;


import com.codedifferently.CD_InternTracker.authentication.TokenUtil;
import com.codedifferently.CD_InternTracker.services.TAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private TAService TAService;
    private TokenUtil tokenUtil;


    @Autowired
    private AuthController(TAService TAService, TokenUtil tokenUtil) {
        this.TAService = TAService;
        this.tokenUtil = tokenUtil;
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {

        String realPass = TAService.getByEmail(email).getPassword();


            if (realPass.equals(password)) {
                String token = tokenUtil.generateToken(email);


                Map<String, String> responseBody = new HashMap<>();

                responseBody.put("accessToken", token);
                responseBody.put("tokenType", "Bearer");
                responseBody.put("expirationTime", Long.toString(tokenUtil.getExpirationTime()));

                return new ResponseEntity<Map<String, String>>(responseBody, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<String>("Invalid credentials", HttpStatus.UNAUTHORIZED);
            }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        return "";
    }
}