package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class AuthController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/login")
  public ResponseEntity<User> login(@RequestBody User loginRequest) {
    Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());
    if (userOpt.isPresent()) {
      User user = userOpt.get();
      if (user.getPassword().equals(loginRequest.getPassword())) {
        // In real app, never store plain passwords and use JWT or sessions
        return ResponseEntity.ok(user);
      }
    }
    return ResponseEntity.status(401).build();
  }
}
