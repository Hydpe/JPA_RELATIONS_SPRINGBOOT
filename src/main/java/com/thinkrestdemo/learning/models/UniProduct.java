package com.thinkrestdemo.learning.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "uni_products")
public class UniProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    private String pName;



    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    @ManyToMany
    private List<UniCategory> category=new ArrayList<UniCategory>();

    public List<UniCategory> getCategory() {
        return category;
    }

    public void setCategory(List<UniCategory> category) {
        this.category = category;
    }
    public UniProduct() {}

    public UniProduct(String pName) {
        this.pName = pName;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }
}
