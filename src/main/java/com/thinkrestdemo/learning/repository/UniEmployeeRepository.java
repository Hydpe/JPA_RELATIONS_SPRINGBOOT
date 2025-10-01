package com.thinkrestdemo.learning.repository;

import com.thinkrestdemo.learning.models.UniEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniEmployeeRepository extends JpaRepository<UniEmployee,Long> {
}
