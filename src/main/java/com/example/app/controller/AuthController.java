package com.example.app.controller;

import com.example.app.dtos.UserDto;
import com.example.app.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/user_register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(userDto));
    }
}
