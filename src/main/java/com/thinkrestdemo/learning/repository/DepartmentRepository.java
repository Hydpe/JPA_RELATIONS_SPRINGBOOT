package com.thinkrestdemo.learning.repository;

import com.thinkrestdemo.learning.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByDepartmentName(String departmentName);

    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.employees WHERE d.departmentId = :departmentId")
    Optional<Department> findByIdWithEmployees(Long departmentId);
}