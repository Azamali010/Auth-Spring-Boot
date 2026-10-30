package com.example.app.controller;


import com.example.app.dtos.UserDto;
import com.example.app.model.User;
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

    // User access using param
    // @GetMapping("/get_users_by_email/{email}")
    @GetMapping("/get_users_by_email")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email){
        System.out.println("Email in Param -------"+ email);
        UserDto userDto = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }

    // User access using body
    /*
    Solution 2: Send enable in your request body
    You can also send:
    {
    "email": "aazam001@example.com",
    "enable": true
    }

    Solution 3: Best design for your use case
    Since your endpoint only needs an email, the cleanest approach is to create a small request DTO:

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class EmailRequest {

    private String email;

    }

    <<<<Controller>>>>
    @PostMapping("/users_by_email")
    public ResponseEntity<UserDto> getUserByEmail(
        @RequestBody EmailRequest request) {

        UserDto response = userService.getUserByEmail(request.getEmail());
        return ResponseEntity.ok(response);

    }
    */
    // Solution 2 At this time
    @PostMapping("/users_by_email")
    public ResponseEntity<UserDto> getUserByEmail(@RequestBody UserDto userDto){
       System.out.println("Email in body-------"+ userDto.getEmail());
       UserDto emailResponse = userService.getUserByEmail(userDto.getEmail());
       return ResponseEntity.ok(emailResponse);
    }

    /*
        if UserDto deleteUser(String userId);
     */
//    @DeleteMapping("/delete_user")
//    public ResponseEntity<UserDto> deleteUser(@RequestParam String userId){
//        UserDto userDto = userService.deleteUser(userId);
//        return ResponseEntity.ok(userDto);
//    }

    /*
    if void deleteUser(String userId);
    Required type:UserDto
    Provided:void
    */
    @DeleteMapping("/delete_user")
    public ResponseEntity<Void> deleteUser(@RequestParam String userId) {

        userService.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }
}
