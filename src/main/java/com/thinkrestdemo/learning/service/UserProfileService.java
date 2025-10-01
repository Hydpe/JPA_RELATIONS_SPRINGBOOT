package com.thinkrestdemo.learning.service;

import com.thinkrestdemo.learning.models.UserProfile;
import com.thinkrestdemo.learning.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;


    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }
    public UserProfile saveUserProfile(UserProfile profile) {
        return userProfileRepository.save(profile);
    }

    public List<UserProfile> getAllProfiles() {
        return userProfileRepository.findAll();
    }


    public Optional<UserProfile> getProfileById(Long id) {
        return userProfileRepository.findById(id);
    }


    public Optional<UserProfile> getProfileWithUser(Long id) {
        return userProfileRepository.findByIdWithUser(id);
    }


    public Optional<UserProfile> getProfileByUserId(Long userId) {
        return userProfileRepository.findByUser_UserId(userId);
    }


    public List<UserProfile> getProfilesByCity(String city) {
        return userProfileRepository.findByCity(city);
    }


    public List<UserProfile> getProfilesByCountry(String country) {
        return userProfileRepository.findByCountry(country);
    }


    public List<UserProfile> getProfilesByName(String firstName, String lastName) {
        return userProfileRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public UserProfile updateProfile(Long id, UserProfile profileDetails) {
        return userProfileRepository.findById(id).map(profile -> {
            profile.setFirstName(profileDetails.getFirstName());
            profile.setLastName(profileDetails.getLastName());
            profile.setPhoneNumber(profileDetails.getPhoneNumber());
            profile.setAddress(profileDetails.getAddress());
            profile.setCity(profileDetails.getCity());
            profile.setCountry(profileDetails.getCountry());
            profile.setDateOfBirth(profileDetails.getDateOfBirth());
            profile.setBio(profileDetails.getBio());
            return userProfileRepository.save(profile);
        }).orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
    }
}
