package com.libraryapp.rest;

import com.libraryapp.entities.User;
import com.libraryapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserByuserId(@PathVariable Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User userDetails) {
        User user = userService.findById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            user.setUserName(userDetails.getUserName());
            user.setPassword(userDetails.getPassword()); // Note: Ne pas mettre à jour le mot de passe de cette manière en production
            user.setEnabled(userDetails.isEnabled());
            user.setRole(userDetails.getRole());
            user.setEmail(userDetails.getEmail());
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setAddress(userDetails.getAddress());
            user.setCity(userDetails.getCity());
            user.setPhoneNumber(userDetails.getPhoneNumber());
            User updatedUser = userService.save(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteById(userId);
            return new ResponseEntity<>("Utilisateur supprimé avec succès", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la suppression de l'utilisateur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
