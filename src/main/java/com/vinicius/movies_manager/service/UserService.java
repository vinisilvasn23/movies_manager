package com.vinicius.movies_manager.service;

import java.util.List;
import java.util.UUID;

import com.vinicius.movies_manager.dto.UserDto;
import com.vinicius.movies_manager.model.User;

public interface UserService {

    UserDto createUser(User userDto);

    List<UserDto> readUsers();

    UserDto retrieveUser(UUID id);

    UserDto partialUpdateUser(UserDto userData, UUID id);

    void deleteUser(UUID id);

    User getByEmail(String email);

}