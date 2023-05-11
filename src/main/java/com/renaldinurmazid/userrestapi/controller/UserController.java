package com.renaldinurmazid.userrestapi.controller;

import com.renaldinurmazid.userrestapi.entity.User;
import com.renaldinurmazid.userrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{userId}")
        public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
            Optional<User> userData = userRepository.findById(userId);

            if (userData.isPresent()) {
                User updatedUser = userData.get();
                updatedUser.setName(user.getName());
                updatedUser.setEmail(user.getEmail());

                userRepository.save(updatedUser);
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.notFound().build();
            }
        }



    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(userId);
            return ResponseEntity.ok("SUCCESS");
        } else {
            return ResponseEntity.badRequest().body("GAGAL");
        }
}
}