package com.app.bugtracker.repository;

import com.app.bugtracker.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;


    @BeforeEach
    public void setUp() {
        user = User.builder().username("test").photoUrl("asdfdf").build();
    }

    @Test
    public void saveAndFindByName(){
        User savedUser = userRepository.save(user);
        assertEquals(savedUser,userRepository.findByUsername(user.getUsername()).get());
    }

    @Test
    public void findById(){
        User savedUser = userRepository.save(user);
        assertEquals(savedUser.getId(),userRepository.findById(savedUser.getId()).get().getId());
    }

    @Test
    public void findAll(){
        userRepository.save(user);
        List<User> users =  userRepository.findAll();

        assertEquals(1,users.size());
    }

}
