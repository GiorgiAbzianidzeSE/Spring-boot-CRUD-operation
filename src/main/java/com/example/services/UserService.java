package com.example.services;

import java.util.List;

import com.example.models.User;
import com.example.models.dto.UserDto;

public interface UserService {

    List<User> getAllUser();

    UserDto getUserById(Long id);

    List<UserDto> getUserByFirstName(String name);

    UserDto createUser(UserDto user);

    UserDto changeUserById(Long id , UserDto user);

    UserDto deleteUserById(Long id);
    
}
