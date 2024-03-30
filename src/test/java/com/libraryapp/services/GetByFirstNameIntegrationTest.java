package com.libraryapp.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.libraryapp.entities.User;
import com.libraryapp.DAO.UserRepository;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test") 
public class GetByFirstNameIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testGetByFirstName() {
        
        User user1 = new User();
        user1.setFirstName("Nouredine");
        userRepository.save(user1);

        User user2 = new User();
        user2.setFirstName("Dicko");
        userRepository.save(user2);

        
        List<User> users = userService.getByFirstName("Nouredine");

        
        assertNotNull(users);
        assertTrue(users.size() >= 1);
        assertTrue(users.stream().anyMatch(user -> user.getFirstName().equalsIgnoreCase("Nouredine")));
    }
}
