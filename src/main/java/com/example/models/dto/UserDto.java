package com.example.models.dto;

import java.time.LocalDate;

import com.example.models.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class UserDto {
    
    private String firstName;
    private String lastName;
    private int age;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;
    private Address address;

    private UserDto(final String firstName , final String lastName , final int age , final LocalDate birthDay , final Address address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthDay = birthDay;
        this.address = address;
    }

    public static UserDto UserToUserDto(final User user){
        return new UserDto(user.getFirstName(), user.getLastName(), user.getAge(), user.getBirthDate(), user.getUserAddress());
    }

    public static UserDto userToUserDtoWithIUserDto(final User user){
        return new UserDto(user.getFirstName(), user.getLastName(), user.getAge(), user.getBirthDate(), user.getUserAddress());
    }


}