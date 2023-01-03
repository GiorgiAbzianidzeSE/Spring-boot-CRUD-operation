package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
    @Query("SELECT u FROM User u WHERE u.firstName = ?1")
    List<User> findByFirstName(String firstName);

    @Query("SELECT u FROM User u WHERE u.age = ?1")
    List<User> findByAge(Integer age);

    @Query("SELECT u FROM User u WHERE u.lastName = ?1")
    List<User> findByLastName(String lastName);
}
