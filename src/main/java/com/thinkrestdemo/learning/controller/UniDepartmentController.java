package com.thinkrestdemo.learning.controller;

import com.thinkrestdemo.learning.models.UniDepartment;
import com.thinkrestdemo.learning.models.UniEmployee;
import com.thinkrestdemo.learning.repository.UniEmployeeRepository;
import com.thinkrestdemo.learning.service.UniDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/unidepartments")
public class UniDepartmentController {

    @Autowired
    private UniDepartmentService departmentService;
    @Autowired
    private UniEmployeeRepository employeeRepository;

    public UniDepartmentController(UniDepartmentService departmentService, UniEmployeeRepository employeeRepository) {
        this.departmentService = departmentService;
        this.employeeRepository=employeeRepository;
    }

    @PostMapping
    public UniDepartment createDepartment(@RequestBody UniDepartment department) {
        return departmentService.saveDepartment(department);
    }

    @GetMapping
    public List<UniDepartment> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Optional<UniDepartment> getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }
    @PutMapping("/{id}")
    public UniDepartment updateDepartment(@PathVariable Long id, @RequestBody UniDepartment updatedDept) {
       Long employeeId;
        Optional<UniDepartment> existingDept = departmentService.getDepartmentById(id);
        if (existingDept.isPresent()){
            UniDepartment dept = existingDept.get();
            dept.setDepartmentName(updatedDept.getDepartmentName());
            dept.setLocation(updatedDept.getLocation());
            dept.setDescription(updatedDept.getDescription());
            if(updatedDept.getEmployees()!=null)
            for(UniEmployee employee : updatedDept.getEmployees())
            {
                          employeeId= employee.getEmployeeId();
                          UniEmployee ExistingEmp=employeeRepository.findById(employeeId).orElseThrow(()-> new RuntimeException("employee id not found"));
                          ExistingEmp.setEmployeeId(employeeId);
                          ExistingEmp.setFirstName(employee.getFirstName());
                          ExistingEmp.setLastName(employee.getLastName());
                          ExistingEmp.setEmail(employee.getEmail());
                          dept.getEmployees().add(ExistingEmp);
            }

            return departmentService.saveDepartment(dept);
        } else {
            throw new RuntimeException("Department not found with id " + id);
        }
    }
    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "Department deleted successfully";
    }
}
