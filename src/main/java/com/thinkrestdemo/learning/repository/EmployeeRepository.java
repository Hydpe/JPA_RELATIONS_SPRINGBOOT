package com.thinkrestdemo.learning.repository;

import com.thinkrestdemo.learning.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    List<Employee> findByDepartment_DepartmentId(Long departmentId);

    @Query("SELECT e FROM Employee e JOIN FETCH e.department WHERE e.employeeId = :employeeId")
    Optional<Employee> findByIdWithDepartment(Long employeeId);
}
