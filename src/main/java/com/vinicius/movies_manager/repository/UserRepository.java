package com.vinicius.movies_manager.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinicius.movies_manager.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsUserByEmail(String email);
    Optional<User> findByEmail(String email);
}