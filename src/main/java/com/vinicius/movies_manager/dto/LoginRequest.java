package com.vinicius.movies_manager.dto;
import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
