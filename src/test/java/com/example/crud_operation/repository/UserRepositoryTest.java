package com.example.crud_operation.repository;

import com.example.models.Address;
import com.example.models.User;
import com.example.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Slf4j
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private static final LocalDate now = LocalDate.now();

    @Test
    void given_emptyDatabase_when_findAll_expect_emptyCollection(){
        Collection<? extends User> users = userRepository.findAll();
        assertThat(users).isEmpty();
    }

    @Test
    void given_databaseWithTwoUsers_when_findAll_expect_collectionWithTwoUsers(){
        final User user1 = User.builder()
                .userAddress(new Address("testStreetName1" , -1 , -1 , false))
                .firstName("testFirstName1")
                .lastName("testLastName1")
                .birthDate(now)
                .age(-1)
                .passowrd("testPassword1")
                .build();

        final User user2 = User.builder()
                .userAddress(new Address("testStreetName2" , -2 , -2 , false))
                .firstName("testFirstName2")
                .lastName("testLastName2")
                .birthDate(now)
                .age(-2)
                .passowrd("testPassword2")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

        assertThat(userRepository.findAll()).isNotEmpty();
        assertThat(userRepository.findAll()).isEqualTo(List.of(user1 , user2));
    }

    @Test
    void given_databaseWithUsers_when_findById_expect_UserWithGivenId(){
        final User user3 = User.builder()
                .userAddress(new Address("testStreetName3" , -3 , -3 , false))
                .firstName("testFirstName3")
                .lastName("testLastName3")
                .birthDate(now)
                .age(-3)
                .passowrd("testPassword3")
                .build();

        userRepository.save(user3);

        Optional<User> s = userRepository.findById(user3.getId());

        assertThat(s).isNotEmpty();
        assertThat(s).hasValue(user3);
    }


    @Test
    void when_save_expect_savedUser(){
        final User user4 = User.builder()
                .userAddress(new Address("testStreetName4" , -4, -4 , false))
                .firstName("testFirstName4")
                .lastName("testLastName4")
                .birthDate(now)
                .age(-4)
                .passowrd("testPassword4")
                .build();

        final User userInDB = userRepository.save(user4);

        assertThat(userInDB).isInstanceOf(User.class);
        assertThat(userInDB.getId()).isEqualTo(user4.getId());
        assertThat(userInDB.getUserAddress().getIsApartment()).isEqualTo(user4.getUserAddress().getIsApartment());
        assertThat(userInDB.getUserAddress().getStreetName()).isEqualTo(user4.getUserAddress().getStreetName());
        assertThat(userInDB.getUserAddress().getStreetNumber()).isEqualTo(user4.getUserAddress().getStreetNumber());
        assertThat(userInDB.getUserAddress().getZipCode()).isEqualTo(user4.getUserAddress().getZipCode());
        assertThat(userInDB.getFirstName()).isEqualTo(user4.getFirstName());
        assertThat(userInDB.getLastName()).isEqualTo(user4.getLastName());
        assertThat(userInDB.getAge()).isEqualTo(user4.getAge());
        assertThat(userInDB.getPassowrd()).isEqualTo(user4.getPassowrd());
        assertThat(userInDB.getBirthDate()).isNotNull();
        assertThat(userInDB.getBirthDate()).isEqualTo(now);
    }

    @Test
    void given_databaseWithUsers_when_save_updatingExistingUser(){
        final User user5 = User.builder()
                .userAddress(new Address("testStreetName5" , -5, -5 , false))
                .firstName("testFirstName5")
                .lastName("testLastName5")
                .birthDate(now)
                .age(-5)
                .passowrd("testPassword5")
                .build();

        userRepository.save(user5);

        final User user6 = User.builder()
                .id(user5.getId())
                .userAddress(new Address("testStreetName6" , -6, -6 , false))
                .firstName("testFirstName6")
                .lastName("testLastName6")
                .birthDate(now)
                .age(-6)
                .passowrd("testPassword6")
                .build();

        userRepository.save(user6);

        final User updatedUser = userRepository.findById(user5.getId()).orElse(null);

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isEqualTo(user6.getId());
        assertThat(updatedUser.getUserAddress().getIsApartment()).isEqualTo(user6.getUserAddress().getIsApartment());
        assertThat(updatedUser.getUserAddress().getStreetName()).isEqualTo(user6.getUserAddress().getStreetName());
        assertThat(updatedUser.getUserAddress().getStreetNumber()).isEqualTo(user6.getUserAddress().getStreetNumber());
        assertThat(updatedUser.getUserAddress().getZipCode()).isEqualTo(user6.getUserAddress().getZipCode());
        assertThat(updatedUser.getFirstName()).isEqualTo(user6.getFirstName());
        assertThat(updatedUser.getLastName()).isEqualTo(user6.getLastName());
        assertThat(updatedUser.getAge()).isEqualTo(user6.getAge());
        assertThat(updatedUser.getPassowrd()).isEqualTo(user6.getPassowrd());
        assertThat(updatedUser.getBirthDate()).isNotNull();
        assertThat(updatedUser.getBirthDate()).isEqualTo(now);
    }

    @Test
    void given_databaseWithUsers_when_deleteById_expect_databaseWithoutUserWithGivenId(){
        final User user7 = User.builder()
                .userAddress(new Address("testStreetName7" , -7 ,-7, false))
                .firstName("testFirstName7")
                .lastName("testLastName7")
                .birthDate(now)
                .age(-7)
                .passowrd("testPassword7")
                .build();

        userRepository.save(user7);

        userRepository.deleteById(user7.getId());

        final Optional<User> deletedUser = userRepository.findById(user7.getId());

        assertThat(deletedUser).isEmpty();
    }

    @Test
    void given_databaseWithUsers_when_deleteAll_expect_emptyDatabase(){
        final User user8 = User.builder()
                .userAddress(new Address("testStreetName8" , -8 ,-8, false))
                .firstName("testFirstName8")
                .lastName("testLastName8")
                .birthDate(now)
                .age(-8)
                .passowrd("testPassword8")
                .build();

        userRepository.save(user8);

        userRepository.deleteAll();

        final Collection<? extends User> users = userRepository.findAll();

        assertThat(users).isEmpty();

    }

    @Test
    void given_databaseWithUsers_when_findByFirstName_expect_listOfTwoUsers(){
        final User user9 = User.builder()
                .userAddress(new Address("testStreetName9" , -9 ,-9, false))
                .firstName("testFirstName9-10")
                .lastName("testLastName9")
                .birthDate(now)
                .age(-9)
                .passowrd("testPassword9")
                .build();

        final User user10 = User.builder()
                .userAddress(new Address("testStreetName10" , -10 ,-10, false))
                .firstName("testFirstName9-10")
                .lastName("testLastName10")
                .birthDate(now)
                .age(-10)
                .passowrd("testPassword10")
                .build();

        userRepository.save(user9);
        userRepository.save(user10);

        final List<? extends User> usersWithSameName = userRepository.findByFirstName(user9.getFirstName());

        assertThat(usersWithSameName).isNotEmpty();
        assertThat(usersWithSameName.size()).isEqualTo(2);
        assertThat(usersWithSameName.get(0)).isInstanceOf(User.class);
        assertThat(usersWithSameName.get(1)).isInstanceOf(User.class);
        assertThat(usersWithSameName.get(1)).isNotNull();
        assertThat(usersWithSameName.get(0)).isNotNull();
        assertThat(usersWithSameName.get(0)).isEqualTo(user9);
        assertThat(usersWithSameName.get(1)).isEqualTo(user10);
    }

    @Test
    void given_databaseWithUsers_when_findByAge_expect_listOfTwoUsers(){
        final User user11 = User.builder()
                .userAddress(new Address("testStreetName11" , -11 ,-11, false))
                .firstName("testFirstName11")
                .lastName("testLastName11")
                .birthDate(now)
                .age(-11)
                .passowrd("testPassword11")
                .build();

        final User user12 = User.builder()
                .userAddress(new Address("testStreetName12" , -12 ,-12, false))
                .firstName("testFirstName11")
                .lastName("testLastName12")
                .birthDate(now)
                .age(-11)
                .passowrd("testPassword12")
                .build();

        userRepository.save(user11);
        userRepository.save(user12);

        final List<? extends User> usersWithSameName = userRepository.findByAge(user11.getAge());

        log.info(user11.toString());
        log.info(user12.toString());

        assertThat(usersWithSameName).isNotEmpty();
        assertThat(usersWithSameName.size()).isEqualTo(2);
        assertThat(usersWithSameName.get(0)).isInstanceOf(User.class);
        assertThat(usersWithSameName.get(1)).isInstanceOf(User.class);
        assertThat(usersWithSameName.get(1)).isNotNull();
        assertThat(usersWithSameName.get(0)).isNotNull();
        assertThat(usersWithSameName.get(0)).isEqualTo(user11);
        assertThat(usersWithSameName.get(1)).isEqualTo(user12);
    }

    @Test
    void given_databaseWithUsers_when_findByLastName_expect_listOfTwoUsers(){
        final User user13 = User.builder()
                .userAddress(new Address("testStreetName13" , -13 ,-13, false))
                .firstName("testFirstName13")
                .lastName("testLastName13-14")
                .birthDate(now)
                .age(-13)
                .passowrd("testPassword13")
                .build();

        final User user14 = User.builder()
                .userAddress(new Address("testStreetName14" , -14 ,-14, false))
                .firstName("testFirstName14")
                .lastName("testLastName13-14")
                .birthDate(now)
                .age(-14)
                .passowrd("testPassword14")
                .build();

        userRepository.save(user13);
        userRepository.save(user14);

        final List<? extends User> usersWithSameName = userRepository.findByLastName(user13.getLastName());

        log.info(user13.toString());
        log.info(user14.toString());

        assertThat(usersWithSameName).isNotEmpty();
        assertThat(usersWithSameName.size()).isEqualTo(2);
        assertThat(usersWithSameName.get(0)).isInstanceOf(User.class);
        assertThat(usersWithSameName.get(1)).isInstanceOf(User.class);
        assertThat(usersWithSameName.get(1)).isNotNull();
        assertThat(usersWithSameName.get(0)).isNotNull();
        assertThat(usersWithSameName.get(0)).isEqualTo(user13);
        assertThat(usersWithSameName.get(1)).isEqualTo(user14);
    }


}
