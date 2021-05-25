package com.moneyview.calendercollab.controller;

import com.moneyview.calendercollab.exception.ResourceNotFoundException;
import com.moneyview.calendercollab.model.User;
import com.moneyview.calendercollab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

//    @RequestMapping("/")
//    String home() {
//        return "Hello from GazGeek!";
//    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        System.out.println("print res: "+user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> archiveUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", userId));

        user.setIsArchived(true);
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }
}
