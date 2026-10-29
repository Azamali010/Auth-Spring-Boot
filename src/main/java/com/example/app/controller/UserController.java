package com.example.app.controller;


import com.example.app.dtos.UserDto;
import com.example.app.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {


   private final UserService userService;

   @PostMapping("/create_users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @GetMapping("/get_users")
    public ResponseEntity<Iterable<UserDto>> getAllUsers(){
       return ResponseEntity.ok(userService.getAllUsers());
    }
}
