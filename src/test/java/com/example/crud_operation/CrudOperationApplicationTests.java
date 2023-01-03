package com.example.crud_operation;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.models.Address;
import com.example.models.User;
import com.example.repositories.UserRepository;
import com.example.services.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
class CrudOperationApplicationTests {

	@MockBean
	UserRepository repository;

	@Autowired
	UserService service;

	@BeforeEach
	@SuppressWarnings("unchecked")
	void loadData(){
		given(repository.saveAll(any(Iterable.class))).willReturn(List.of(
			new User("Spring" , "boot" , 11 , LocalDate.of(2000 , 2 , 2) , new Address("NewYorkStree" , 1 , 102 , true)),
		new User(".NET" , "MVC" , 12 , LocalDate.of(2002 , 1 , 4) , new Address("BerlinStreet" , 2 , 111 , false))
		));
	}
	@AfterEach
	void clearData(){
		this.repository.deleteAll();
	}


	@Test
	void allUsersTest(){
		assertEquals(repository.findAll() , service.getAllUser());
	}


}
