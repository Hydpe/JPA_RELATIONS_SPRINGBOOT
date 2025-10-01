package com.thinkrestdemo.learning.service;

import com.thinkrestdemo.learning.models.Category;
import com.thinkrestdemo.learning.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Optional<Category> getCategoryById(String id) {
        return categoryRepo.findById(id);
    }
    public Category updateCategory(String id, Category categoryDetails) {

        Category category = categoryRepo.findById(id).orElse(null);


        if (category == null) {
            throw new RuntimeException("Category not found with id: " + id);
        }

        category.setTitle(categoryDetails.getTitle());

        if (categoryDetails.getProducts() != null) {
            category.setProducts(categoryDetails.getProducts());
        }
        return categoryRepo.save(category);
    }

    public void deleteCategory(String id) {
        if (!categoryRepo.existsById(id)) {

            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepo.deleteById(id);
    }
}