package com.thinkrestdemo.learning.repository;

import com.thinkrestdemo.learning.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);


    Optional<User> findByEmail(String email);


    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userProfile WHERE u.userId = :userId")
    Optional<User> findByIdWithProfile(@Param("userId") Long userId);


    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userProfile")
    List<User> findAllWithProfiles();


    boolean existsByUsername(String username);


    boolean existsByEmail(String email);
}