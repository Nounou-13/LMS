package com.libraryapp.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.libraryapp.entities.User;
import com.libraryapp.DAO.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test") 
public class UserServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testFindById() {
        
        User user = new User();
        user.setUserName("testUser");
        userRepository.save(user);

        
        User foundUser = userService.findById(user.getUserId());

        
        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUserName());
    }
}
