package com.vinicius.movies_manager.controller;

import com.vinicius.movies_manager.config.TokenService;
import com.vinicius.movies_manager.dto.MovieDto;
import com.vinicius.movies_manager.service.MovieService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;
    private TokenService tokenService;

    public MovieController(MovieService movieService, TokenService tokenService) {
        this.movieService = movieService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(
            @Valid @RequestBody MovieDto movieDto,
            @RequestHeader(name = "Authorization") String token) {
        String userEmail = tokenService.validateToken(token);
        MovieDto createdMovie = movieService.createMovie(movieDto, userEmail);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> readMovies() {
        List<MovieDto> allMovies = movieService.readMovies();
        return new ResponseEntity<>(allMovies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> retrieveMovie(@PathVariable UUID id) {
        MovieDto movie = movieService.retrieveMovie(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieDto> partialUpdateMovie(@PathVariable UUID id, @RequestBody MovieDto updatedMovieDto,
            @RequestHeader(name = "Authorization") String token) {
        String userEmail = tokenService.validateToken(token);
        MovieDto movieToUpdate = movieService.retrieveMovie(id);

        if (!movieToUpdate.getUser().getEmail().equals(userEmail)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        MovieDto updatedMovie = movieService.partialUpdateMovie(updatedMovieDto, id);
        return ResponseEntity.ok(updatedMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id,
            @RequestHeader(name = "Authorization") String token) {
        String userEmail = tokenService.validateToken(token);
        MovieDto movieToDelete = movieService.retrieveMovie(id);

        if (!movieToDelete.getUser().getEmail().equals(userEmail)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
