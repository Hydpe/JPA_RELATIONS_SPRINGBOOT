package com.thinkrestdemo.learning.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jpa_product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "pid")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;
    private String pname;


    @ManyToMany(mappedBy = "products")
    private List<Category> categories = new ArrayList<>();

    public Product() {
    }

    public Product(long pid, String pname) {
        this.pid = pid;
        this.pname = pname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
