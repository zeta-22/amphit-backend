package com.amphit.backend.controller;

import com.amphit.backend.dto.PasswordChangeRequest;
import com.amphit.backend.dto.UserProfileRequest;
import com.amphit.backend.model.User;
import com.amphit.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(auth.getName());
        
        // Remove sensitive information
        user.setPassword(null);
        
        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateUserProfile(@Valid @RequestBody UserProfileRequest profileRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(auth.getName());
        
        user = userService.updateUserProfile(
                user.getId(),
                profileRequest.getFirstName(),
                profileRequest.getLastName(),
                profileRequest.getEmail()
        );
        
        // Remove sensitive information
        user.setPassword(null);
        
        return ResponseEntity.ok(user);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordChangeRequest passwordRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(auth.getName());
        
        userService.changePassword(
                user.getId(),
                passwordRequest.getCurrentPassword(),
                passwordRequest.getNewPassword()
        );
        
        return ResponseEntity.ok("Password changed successfully!");
    }
}
