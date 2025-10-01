package com.thinkrestdemo.learning.service;

import com.thinkrestdemo.learning.models.Department;
import com.thinkrestdemo.learning.models.Employee;
import com.thinkrestdemo.learning.repository.DepartmentRepository;
import com.thinkrestdemo.learning.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }


    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }


    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }


    public Optional<Department> getDepartmentWithEmployees(Long id) {
        return departmentRepository.findByIdWithEmployees(id);
    }


    public Department updateDepartment(Long id, Department departmentDetails) {
        return departmentRepository.findById(id).map(department -> {
            department.setDepartmentName(departmentDetails.getDepartmentName());
            department.setLocation(departmentDetails.getLocation());
            department.setDescription(departmentDetails.getDescription());

            if (departmentDetails.getEmployees() != null) {
                department.getEmployees().clear();
                for (Employee emp : departmentDetails.getEmployees()) {
                    emp.setDepartment(department);
                    department.getEmployees().add(emp);
                }
            }

            return departmentRepository.save(department);
        }).orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }



    public Department addEmployeeToDepartment(Long departmentId, Employee employee) {
        return departmentRepository.findById(departmentId).map(department -> {
            employee.setDepartment(department);
            Employee savedEmployee = employeeRepository.save(employee);
            department.addEmployee(savedEmployee);
            return departmentRepository.save(department);
        }).orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));
    }


    public void deleteDepartment(Long id) {
        Optional<Department> departmentOpt = departmentRepository.findById(id);
        if (departmentOpt.isPresent()) {
            Department department = departmentOpt.get();

            for (Employee employee : department.getEmployees()) {
                employee.setDepartment(null);
                employeeRepository.save(employee);
            }
            departmentRepository.delete(department);
        } else {
            throw new RuntimeException("Department not found with id: " + id);
        }
    }
}