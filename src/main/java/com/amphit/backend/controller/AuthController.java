package com.amphit.backend.controller;

import com.amphit.backend.dto.JwtResponse;
import com.amphit.backend.dto.LoginRequest;
import com.amphit.backend.dto.RegisterRequest;
import com.amphit.backend.model.User;
import com.amphit.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String jwt = authService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        
        // We would typically retrieve the user details here for the response
        // For simplicity, we're just returning the token
        return ResponseEntity.ok(new JwtResponse(jwt, null, loginRequest.getUsername(), null));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = authService.registerUser(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getFirstName(),
                registerRequest.getLastName()
        );
        
        return ResponseEntity.ok("User " + user.getUsername() + " registered successfully!");
    }
}
