package com.vinicius.movies_manager.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MovieDto {
    private UUID id;

    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title cannot be empty")
    @Size(max = 127, message = "Title must be lower than 128 characters long")
    private String title;

    @Size(max = 10, message = "Duration must be lower than 11 characters long")
    private String duration;

    @NotNull(message = "Price is required")
    private Integer price;

    @NotNull(message = "Description is required")
    @Size(min = 10, message = "The description must be at least 10 characters long")
    private String description;

    private UserDto user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
