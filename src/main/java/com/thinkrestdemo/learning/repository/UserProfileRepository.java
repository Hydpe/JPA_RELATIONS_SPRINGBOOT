package com.thinkrestdemo.learning.repository;

import com.thinkrestdemo.learning.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {


    Optional<UserProfile> findByUser_UserId(Long userId);


    List<UserProfile> findByCity(String city);

    List<UserProfile> findByCountry(String country);


    @Query("SELECT up FROM UserProfile up JOIN FETCH up.user WHERE up.profileId = :profileId")
    Optional<UserProfile> findByIdWithUser(@Param("profileId") Long profileId);


    List<UserProfile> findByFirstNameAndLastName(String firstName, String lastName);
}