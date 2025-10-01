package com.thinkrestdemo.learning.repository;

import com.thinkrestdemo.learning.models.UniUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniUserRepository extends JpaRepository<UniUser, Long> {
}
