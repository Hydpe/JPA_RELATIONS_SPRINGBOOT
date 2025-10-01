package com.thinkrestdemo.learning.service;

import com.thinkrestdemo.learning.models.UniDepartment;
import com.thinkrestdemo.learning.repository.UniDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniDepartmentService {

    @Autowired
    private final UniDepartmentRepository departmentRepository;

    public UniDepartmentService(UniDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public UniDepartment saveDepartment(UniDepartment department) {
        return departmentRepository.save(department);
    }


    public List<UniDepartment> getAllDepartments() {
        return departmentRepository.findAll();
    }


    public Optional<UniDepartment> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }


    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
