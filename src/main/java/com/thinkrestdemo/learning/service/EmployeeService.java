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
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }


    public Employee createEmployee(Employee employee)
    {
        Department department = departmentRepository.findById(employee.getDepartment().getDepartmentId())
                .orElse(null);
        if(department!=null)
        {
            employee.setDepartment(department);
            department.addEmployee(employee);
            return employeeRepository.save(employee);
        }
        department=new Department();
        department.setDepartmentName(employee.getDepartment().getDepartmentName());
        departmentRepository.save(department);
        employee.setDepartment(department);
        department.addEmployee(employee);
        return employeeRepository.save(employee);
    }



    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }


    public Optional<Employee> getEmployeeWithDepartment(Long id) {
        return employeeRepository.findByIdWithDepartment(id);
    }


    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartment_DepartmentId(departmentId);
    }


    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPosition(employeeDetails.getPosition());
        employee.setSalary(employeeDetails.getSalary());
        employee.setHireDate(employeeDetails.getHireDate());
        Department department = departmentRepository.findById(employeeDetails.getDepartment().getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }




    public void deleteEmployee(Long id) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            // Remove employee from department
            if (employee.getDepartment() != null) {
                employee.getDepartment().removeEmployee(employee);
            }
            employeeRepository.delete(employee);
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }
}
