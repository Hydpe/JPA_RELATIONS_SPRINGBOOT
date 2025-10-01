package com.thinkrestdemo.learning.service;

import com.thinkrestdemo.learning.models.UniUser;
import com.thinkrestdemo.learning.models.UniUserProfile;
import com.thinkrestdemo.learning.repository.UniUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UniUserService {

    @Autowired
    private UniUserRepository uniUserRepository;


    public UniUserService(UniUserRepository uniUserRepository) {
        this.uniUserRepository = uniUserRepository;
    }
    public UniUser createUserWithProfile(UniUser user, UniUserProfile profile) {
        user.setProfile(profile);
        return uniUserRepository.save(user);
    }


    public List<UniUser> getAllUsers() {
        return uniUserRepository.findAll();
    }

    public Optional<UniUser> getUserById(Long id) {
        return uniUserRepository.findById(id);
    }


    public UniUser addProfileToUser(Long userId, UniUserProfile profile) {
        return uniUserRepository.findById(userId).map(user -> {
            user.setProfile(profile);
            return uniUserRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }


    public UniUser updateUserProfile(Long userId, UniUserProfile profileDetails) {
        return uniUserRepository.findById(userId).map(user -> {
            UniUserProfile profile = user.getProfile();
            if (profile == null) {
                throw new RuntimeException("User has no profile to update");
            }
            profile.setFirstName(profileDetails.getFirstName());
            profile.setLastName(profileDetails.getLastName());

            return uniUserRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }


    public UniUser deleteUserProfile(Long userId) {
        return uniUserRepository.findById(userId).map(user -> {
            user.setProfile(null);
            return uniUserRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }


    public void deleteUser(Long id) {
        if (!uniUserRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        uniUserRepository.deleteById(id);
    }
}