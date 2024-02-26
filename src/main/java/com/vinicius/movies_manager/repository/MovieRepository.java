package com.vinicius.movies_manager.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinicius.movies_manager.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
}