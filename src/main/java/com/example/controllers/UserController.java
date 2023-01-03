package com.example.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.models.User;
import com.example.models.dto.UserDto;
import com.example.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService service;

    public UserController(final UserService service){
        this.service = service;
    }
    
    @GetMapping
    public List<User> getAllUser(){
        return service.getAllUser();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long id){
        return this.service.getUserById(id);
    }

    @GetMapping("/")
    public List<UserDto> getUserByFirstName(@RequestParam String name){
        return this.service.getUserByFirstName(name);
    }

    @PostMapping()
    public UserDto createUser(@RequestBody UserDto user){
        System.out.println(user.getAddress());
        return this.service.createUser(user);
    }

    @PutMapping("/{id}")
    public UserDto changeUserById(@PathVariable("id") final Long id , @RequestBody UserDto user){
        return this.service.changeUserById(id , user);
    }

    @DeleteMapping("{id}")
    public UserDto deleteUserById(@PathVariable("id") final Long id){
        return this.service.deleteUserById(id);
    }


}
