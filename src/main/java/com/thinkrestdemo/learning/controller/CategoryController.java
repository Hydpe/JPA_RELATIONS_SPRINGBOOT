// File 1: CategoryController.java
package com.thinkrestdemo.learning.controller;

import com.thinkrestdemo.learning.models.Category;
import com.thinkrestdemo.learning.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category)
    {
        return ResponseEntity.ok(categoryService.saveCategory(category));
    }


    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable String id) {

        Category category = categoryService.getCategoryById(id).orElse(null);


        if (category != null)
        {
            return ResponseEntity.ok(category);
        }
        else
        {

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable String id, @RequestBody Category categoryDetails) {
        try {

            Category updatedCategory = categoryService.updateCategory(id, categoryDetails);


            return ResponseEntity.ok(updatedCategory);

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) {

        Optional<Category> categoryOptional = categoryService.getCategoryById(id);


        if (categoryOptional.isPresent()) {

            categoryService.deleteCategory(id);

            String message = "Category  " + id + " deleted successfully!";
            return ResponseEntity.ok(message);
        } else {

            String errorMessage = "Category " + id + " not found";
            return ResponseEntity.status(404).body(errorMessage);
        }
    }

}
