package com.thinkrestdemo.learning.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Uni_Categories")
public class UniCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String category;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "uni_category_product",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<UniProduct> products = new ArrayList<>();

    public UniCategory() {}

    public UniCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<UniProduct> getProducts() {
        return products;
    }

    public void setProducts(List<UniProduct> products) {
        this.products = products;
    }

    public void addProduct(UniProduct product) {
        this.products.add(product);
    }
}
