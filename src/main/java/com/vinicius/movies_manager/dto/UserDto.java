package com.vinicius.movies_manager.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {

    private UUID id;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email cannot be empty")
    @Size(max = 62, message = "Email must be lower than 62 characters long")
    private String email;

    @JsonIgnore
    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "FirstName is required")
    @NotEmpty(message = "FirstName cannot be empty")
    @Size(max = 20, message = "FirstName must be lower than 20 characters long")
    private String firstName;

    @NotNull(message = "LastName is required")
    @NotEmpty(message = "LastName cannot be empty")
    @Size(max = 40, message = "LastName must be lower than 40 characters long")
    private String lastName;

    @NotNull(message = "Birthdate is required")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Invalid date format. Use dd-MM-dd format.")
    private String birthdate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
