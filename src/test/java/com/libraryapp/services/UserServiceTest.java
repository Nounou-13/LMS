package com.libraryapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.libraryapp.DAO.UserRepository;
import com.libraryapp.entities.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindAll() {
        User user1 = new User();
        user1.setUserName("user1");
        user1.setPassword("password1");
        user1.setEmail("user1@example.com");
        user1.setFirstName("User");
        user1.setLastName("One");
        User user2 = new User();
        user2.setUserName("user2");
        user2.setPassword("password2");
        user2.setEmail("user2@example.com");
        user2.setFirstName("User");
        user2.setLastName("Two");
        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> foundUsers = userService.findAll();

        assertEquals(2, foundUsers.size());
        assertEquals("user1", foundUsers.get(0).getUserName());
        assertEquals("password1", foundUsers.get(0).getPassword());
        assertEquals("user1@example.com", foundUsers.get(0).getEmail());
        assertEquals("User", foundUsers.get(0).getFirstName());
        assertEquals("One", foundUsers.get(0).getLastName());
        assertEquals("user2", foundUsers.get(1).getUserName());
        assertEquals("password2", foundUsers.get(1).getPassword());
        assertEquals("user2@example.com", foundUsers.get(1).getEmail());
        assertEquals("User", foundUsers.get(1).getFirstName());
        assertEquals("Two", foundUsers.get(1).getLastName());
    }
}
