package com.vinicius.movies_manager.service.impl;

import com.vinicius.movies_manager.dto.UserDto;
import com.vinicius.movies_manager.exception.AppException;
import com.vinicius.movies_manager.model.User;
import com.vinicius.movies_manager.repository.UserRepository;
import com.vinicius.movies_manager.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
            ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    private void checkEmail(final User userData) {
        if (userRepository.existsUserByEmail(userData.getEmail())) {
            throw new AppException("emailAlreadyInUse", HttpStatus.CONFLICT);
        }
    }

    public User getByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));
        return modelMapper.map(user, User.class);
    }

    @Override
    public UserDto createUser(User userData) {
        checkEmail(userData);
        User userEntity = modelMapper.map(userData, User.class);
        userEntity.setPassword(passwordEncoder.encode(userData.getPassword()));
        userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDto.class);
    }

    public List<UserDto> readUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto retrieveUser(UUID id) {
        @SuppressWarnings("null")
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));
        return modelMapper.map(user, UserDto.class);
    }
    
    public UserDto partialUpdateUser(UserDto userData, UUID id) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));
    
        if (userData.getEmail() != null) {
            userToUpdate.setEmail(userData.getEmail());
        }
    
        if (userData.getPassword() != null) {
            String encryptedPassword = passwordEncoder.encode(userData.getPassword());
            userToUpdate.setPassword(encryptedPassword);
        }
    
        if (userData.getFirstName() != null) {
            userToUpdate.setFirstName(userData.getFirstName());
        }
    
        if (userData.getLastName() != null) {
            userToUpdate.setLastName(userData.getLastName());
        }
    
        if (userData.getBirthdate() != null) {
            userToUpdate.setBirthdate(userData.getBirthdate());
        }

    
        User updatedUser = userRepository.save(userToUpdate);
        return modelMapper.map(updatedUser, UserDto.class);
    }
    public void deleteUser(UUID id) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));
        userRepository.delete(foundUser);
    }
}
