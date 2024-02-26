package com.vinicius.movies_manager.service;

import com.vinicius.movies_manager.dto.MovieDto;
import java.util.List;
import java.util.UUID;

public interface MovieService {
    MovieDto createMovie(MovieDto movieDto, String userEmail);
    List<MovieDto> readMovies();
    MovieDto retrieveMovie(UUID id);
    MovieDto partialUpdateMovie(MovieDto updatedMovieDto, UUID id);
    void deleteMovie(UUID id);
}
