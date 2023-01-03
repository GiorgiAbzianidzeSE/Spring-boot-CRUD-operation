package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.models.*;
import com.example.models.dto.UserDto;

import org.springframework.stereotype.Service;

import com.example.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    
    public final UserRepository repository;

    public UserServiceImpl(final UserRepository repository){
        this.repository = repository;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = this.repository.findAll();
        return users;//users.stream().map(UserDto::UserToUserDto).toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = this.repository.findById(id);
        return user.map(UserDto::UserToUserDto).orElseThrow(() -> new RuntimeException("not such id: " + id));
    }

    @Override
    public List<UserDto> getUserByFirstName(String name) {
        List<User> users = this.repository.findByFirstName(name);
        return users.stream().map(UserDto::UserToUserDto).toList();
    }

    @Override
    public UserDto createUser(UserDto user) {
        this.repository.save(User.userDtoToUser(user));
        return user;

    }

    @Override
    public UserDto changeUserById(Long id , UserDto user) {
        getUserById(id); //validation if user exist with given id
        System.out.println(User.userDtoToUserWithId(user, id));
        return UserDto.UserToUserDto(this.repository.save(User.userDtoToUserWithId(user, id)));
    }

    @Override
    public UserDto deleteUserById(Long id) {
        final UserDto userInDB = getUserById(id);
        this.repository.deleteById(id);
        return userInDB;
    }

    
}
