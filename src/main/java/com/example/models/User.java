package com.example.models;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import com.example.models.dto.UserDto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_table")
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class User{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(name = "first_name" , nullable = false , length = 40)
    private String firstName;

    @Column(name = "last_name" , nullable = false , length = 40)
    private String lastName;

    //Never save password in String in production -- it's jsut demonstration
    @Column(name = "user_password" , nullable = false)
    private String passowrd = UUID.randomUUID().toString();
    
    @Column(name = "user_age" , nullable = false)
    private Integer age;

    @Column(name = "user_birth_day" , nullable = false)
    private LocalDate birthDate;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "streetName" , column = @Column(name = "user_street_name" , length = 40 , nullable = false)),
        @AttributeOverride(name = "streetNumber" , column = @Column(name = "user_street_number"  , nullable = false)),
        @AttributeOverride(name = "zipCode" , column = @Column(name = "user_zip_code"  , nullable = false)),
        @AttributeOverride(name = "isApartment" , column = @Column(name = "live_in_apartment" , nullable = false))
    })
    private Address userAddress;


    public User(final String firstName , final String lastName , final Integer age , final LocalDate birthDate , final Address userAddress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.userAddress = userAddress;
        id = -1L;
    }

    public User(final Long id , final String firstName ,  final String lastName , final String passowrd  , final Integer age , final LocalDate birthDate , final Address userAddress){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.userAddress = userAddress;
        this.passowrd = passowrd;
    }

    protected User(){
        id = -1L;
    }

    public static User userDtoToUser(final UserDto userDto){
        return new User(userDto.getFirstName(), userDto.getLastName(), userDto.getAge(), userDto.getBirthDay(), userDto.getAddress());
    }

    public static User userDtoToUserWithId(final UserDto userDto , final Long id){
        return new User(id , userDto.getFirstName(), "defaultPassword" ,  userDto.getLastName(), userDto.getAge(), userDto.getBirthDay(), userDto.getAddress());
    }

}