package com.thinkrestdemo.learning.service;

import com.thinkrestdemo.learning.models.User;
import com.thinkrestdemo.learning.models.UserProfile;
import com.thinkrestdemo.learning.repository.UserRepository;
import com.thinkrestdemo.learning.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserService(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists: " + user.getEmail());
        }
        return userRepository.save(user);
    }


    public User createUserWithProfile(User user, UserProfile profile) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists: " + user.getEmail());
        }

        user.setUserProfile(profile);
        profile.setUser(user);

        return userRepository.save(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public List<User> getAllUsersWithProfiles() {
        return userRepository.findAllWithProfiles();
    }


    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public Optional<User> getUserWithProfile(Long id) {
        return userRepository.findByIdWithProfile(id);
    }


    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            if (userDetails.getPassword() != null) {
                user.setPassword(userDetails.getPassword());
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }


    public User addProfileToUser(Long userId, UserProfile profile) {
        return userRepository.findById(userId).map(user -> {
            if (user.getUserProfile() != null) {
                throw new RuntimeException("User already has a profile");
            }
            user.setUserProfile(profile);
            profile.setUser(user);
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }


    public User updateUserProfile(Long userId, UserProfile profileDetails) {
        return userRepository.findById(userId).map(user -> {

            UserProfile existingProfile = user.getUserProfile();
            if (existingProfile == null) {
                throw new RuntimeException("User does not have a profile to update");
            }

            existingProfile.setFirstName(profileDetails.getFirstName());
            existingProfile.setLastName(profileDetails.getLastName());
            existingProfile.setPhoneNumber(profileDetails.getPhoneNumber());
            existingProfile.setAddress(profileDetails.getAddress());
            existingProfile.setCity(profileDetails.getCity());
            existingProfile.setCountry(profileDetails.getCountry());
            existingProfile.setDateOfBirth(profileDetails.getDateOfBirth());
            existingProfile.setBio(profileDetails.getBio());

            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }


    public User deleteUserProfile(Long userId) {
        return userRepository.findById(userId).map(user -> {
            if (user.getUserProfile() == null) {
                throw new RuntimeException("User does not have a profile to delete");
            }
            user.setUserProfile(null);
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}