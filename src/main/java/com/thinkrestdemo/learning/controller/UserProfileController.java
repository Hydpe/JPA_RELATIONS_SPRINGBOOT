package com.thinkrestdemo.learning.controller;

import com.thinkrestdemo.learning.models.User;
import com.thinkrestdemo.learning.models.UserProfile;
import com.thinkrestdemo.learning.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public ResponseEntity<?> createUserProfile(@RequestBody UserProfile userProfile) {
        try {

            User user = userProfile.getUser();

            if (user == null) {
                return ResponseEntity.badRequest().body("User details missing");
            }

            user.setUserProfile(userProfile);

            userProfile.setUser(user);

            UserProfile savedProfile = userProfileService.saveUserProfile(userProfile);

            return ResponseEntity.ok(savedProfile);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error creating user profile");
        }
    }


    @GetMapping
    public List<UserProfile> getAllProfiles() {
        return userProfileService.getAllProfiles();
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getProfileById(@PathVariable Long id) {
        return userProfileService.getProfileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/name")
    public List<UserProfile> getProfilesByName(@RequestParam String firstName, @RequestParam String lastName) {
        return userProfileService.getProfilesByName(firstName, lastName);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> updateProfile(@PathVariable Long id, @RequestBody UserProfile profileDetails) {
        try {
            return ResponseEntity.ok(userProfileService.updateProfile(id, profileDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}