package com.vinicius.movies_manager.service.impl;

import com.vinicius.movies_manager.dto.MovieDto;
import com.vinicius.movies_manager.dto.UserDto;
import com.vinicius.movies_manager.exception.AppException;
import com.vinicius.movies_manager.model.Movie;
import com.vinicius.movies_manager.model.User;
import com.vinicius.movies_manager.repository.MovieRepository;
import com.vinicius.movies_manager.repository.UserRepository;
import com.vinicius.movies_manager.service.MovieService;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository movieRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    private User getCurrentUser(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
    }

    public MovieDto createMovie(MovieDto movieData, String userEmail) {
        User currentUser = getCurrentUser(userEmail);
        Movie newMovie = modelMapper.map(movieData, Movie.class);
        newMovie.setUser(currentUser);
        Movie savedMovie = movieRepository.save(newMovie);

        return modelMapper.map(savedMovie, MovieDto.class);
    }

    public List<MovieDto> readMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
    }

    public MovieDto retrieveMovie(UUID id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new AppException("Movie not found", HttpStatus.NOT_FOUND));

        return modelMapper.map(movie, MovieDto.class);
    }

    public MovieDto partialUpdateMovie(MovieDto updatedMovieDto, UUID id) {
        Movie movieToUpdate = movieRepository.findById(id)
                .orElseThrow(() -> new AppException("Movie not found", HttpStatus.NOT_FOUND));

        if (updatedMovieDto.getTitle() != null) {
            movieToUpdate.setTitle(updatedMovieDto.getTitle());
        }
        if (updatedMovieDto.getDuration() != null) {
            movieToUpdate.setDuration(updatedMovieDto.getDuration());
        }
        if (updatedMovieDto.getPrice() != null) {
            movieToUpdate.setPrice(updatedMovieDto.getPrice());
        }
        if (updatedMovieDto.getDescription() != null) {
            movieToUpdate.setDescription(updatedMovieDto.getDescription());
        }

        Movie movie = movieRepository.save(movieToUpdate);
        return modelMapper.map(movie, MovieDto.class);
    }

    public void deleteMovie(UUID id) {
        Movie movieToDelete = movieRepository.findById(id)
                .orElseThrow(() -> new AppException("Movie not found", HttpStatus.NOT_FOUND));
        movieRepository.delete(movieToDelete);
    }
}
