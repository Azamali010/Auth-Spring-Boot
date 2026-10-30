package com.example.app.services.impl;

import com.example.app.dtos.UserDto;
import com.example.app.model.Provider;
import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import com.example.app.services.AuthService;
import com.example.app.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public UserDto registerUser(UserDto userDto) {

        // Register API Logic
        // verify email
        // verify password
        // Role

        UserDto userDto1 = userService.createUser(userDto);
        return userDto1;
    }
}
