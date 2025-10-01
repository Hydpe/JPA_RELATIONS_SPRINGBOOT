package com.thinkrestdemo.learning.repository;

import com.thinkrestdemo.learning.models.UniDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniDepartmentRepository extends JpaRepository<UniDepartment, Long> {
}
