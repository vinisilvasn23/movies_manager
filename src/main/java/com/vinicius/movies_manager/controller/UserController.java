package com.vinicius.movies_manager.controller;

import com.vinicius.movies_manager.config.TokenService;
import com.vinicius.movies_manager.dto.UserDto;
import com.vinicius.movies_manager.model.User;
import com.vinicius.movies_manager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private TokenService tokenService;

    public UserController(
            UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody User userdata) {
        UserDto createdUser = userService.createUser(userdata);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> readUsers() {
        List<UserDto> allUsers = userService.readUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> retrieveUser(@PathVariable UUID id) {

        UserDto user = userService.retrieveUser(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> partialUpdateUser(
            @PathVariable UUID id,
            @RequestBody UserDto updatedUserDto,
            @RequestHeader(name = "Authorization") String token) {
        String userEmail = tokenService.validateToken(token);
        User authenticatedUser = userService.getByEmail(userEmail);
        
        if (!authenticatedUser.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDto updated = userService.partialUpdateUser(updatedUserDto, id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id,
            @RequestHeader(name = "Authorization") String token) {
        String userEmail = tokenService.validateToken(token);

        User authenticatedUser = userService.getByEmail(userEmail);
        if (!authenticatedUser.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}