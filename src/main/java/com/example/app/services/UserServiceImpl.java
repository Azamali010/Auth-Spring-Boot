package com.example.app.services;

import com.example.app.dtos.UserDto;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.helpers.UserHelper;
import com.example.app.model.Provider;
import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {

        if (userDto.getEmail()==null || userDto.getEmail().isBlank()){
            throw new IllegalArgumentException("Email is required");
        }

        if (userRepository.existsByEmail(userDto.getEmail())){
            throw new IllegalArgumentException("Email already exists ");
        }

        User user = modelMapper.map(userDto, User.class);
        user.setProvider(userDto.getProvider()!=null ? userDto.getProvider() : Provider.LOCAL);

        userRepository.save(user);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with Email : " + email));
        return modelMapper.map(user, UserDto.class);

    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {
        UUID uId = UserHelper.parseUUID(userId);
        User user = userRepository.findById(uId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with Email : " + userId));
        userRepository.delete(user);

    }
    /*
    if UserDto deleteUser(String userId);
    @Override
    public UserDto deleteUser(String userId) {

    UUID uId = UserHelper.parseUUID(userId);
    User user = userRepository.findById(uId)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "User not found with ID: " + userId
                    ));

    UserDto userDto = modelMapper.map(user, UserDto.class);
    userRepository.delete(user);
    return userDto;
    }
     */

    @Override
    public UserDto getUserById(String userId) {

        UUID uId = UserHelper.parseUUID(userId);
        User user = userRepository.findById(uId).orElseThrow(() -> new ResourceNotFoundException("User Not Found With This Id : " + userId));
        return modelMapper.map(user, UserDto.class);

    }

    @Transactional
    @Override
    public Iterable<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }
}
