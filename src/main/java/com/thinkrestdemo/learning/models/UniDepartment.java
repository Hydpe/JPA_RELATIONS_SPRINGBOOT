package com.thinkrestdemo.learning.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "uni_departments")
public class UniDepartment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Column(nullable = false)
    private String departmentName;

    private String location;
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id") // FK in uni_employees table
    private List<UniEmployee> employees = new ArrayList<>();

    public UniDepartment() {}

    public UniDepartment(String departmentName, String location, String description) {
        this.departmentName = departmentName;
        this.location = location;
        this.description = description;
    }


    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UniEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<UniEmployee> employees) {
        this.employees = employees;
    }

    public void addEmployee(UniEmployee employee) {
        employees.add(employee);
    }

    public void removeEmployee(UniEmployee employee) {
        employees.remove(employee);
    }
}
