package com.thinkrestdemo.learning.controller;

import com.thinkrestdemo.learning.models.UniUser;
import com.thinkrestdemo.learning.models.UniUserProfile;
import com.thinkrestdemo.learning.service.UniUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/uniusers")
public class UniUserController {

    @Autowired
    private UniUserService uniUserService;

    public UniUserController(UniUserService uniUserService) {
        this.uniUserService = uniUserService;
    }
    @PostMapping
    public UniUser createUser(@RequestBody UniUser user) {
        return uniUserService.createUserWithProfile(user, user.getProfile());
    }

    @GetMapping
    public List<UniUser> getAllUsers() {
        return uniUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<UniUser> getUserById(@PathVariable Long id) {
        return uniUserService.getUserById(id);
    }


    @PutMapping("/{id}")
    public UniUser updateProfile(@PathVariable Long id, @RequestBody UniUserProfile profile) {
        return uniUserService.updateUserProfile(id, profile);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        uniUserService.deleteUser(id);
        return "User deleted successfully";
    }
}
