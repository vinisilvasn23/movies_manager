package com.vinicius.movies_manager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.movies_manager.dto.LoginRequest;
import com.vinicius.movies_manager.service.AuthorizationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final AuthorizationService authService;

    public AuthController(AuthorizationService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        return authService.login(login);
    }
}