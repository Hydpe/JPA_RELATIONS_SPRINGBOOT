package com.thinkrestdemo.learning.controller;


import com.thinkrestdemo.learning.models.UniCategory;
import com.thinkrestdemo.learning.models.UniProduct;
import com.thinkrestdemo.learning.service.CategoryService;
import com.thinkrestdemo.learning.service.ProductService;
import com.thinkrestdemo.learning.service.UniCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/unicategories")
public class UniCategoryController {

    private final UniCategoryService uniCategoryService;

    @Autowired
    public UniCategoryController(UniCategoryService uniCategoryService) {
        this.uniCategoryService = uniCategoryService;
    }

    @PostMapping
    public UniCategory createCategory(@RequestBody UniCategory category) {
        return uniCategoryService.createCategory(category);
    }

    @GetMapping
    public List<UniCategory> getAllCategories() {
        return uniCategoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public UniCategory getCategoryById(@PathVariable int id) {
        return uniCategoryService.getCategoryById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    @PutMapping("/{id}")
    public UniCategory updateCategory(@PathVariable int id, @RequestBody UniCategory updatedCategory) {
        return uniCategoryService.updateCategory(id, updatedCategory);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable int id) {
        uniCategoryService.deleteCategory(id);
        return "Category deleted successfully";
    }

    @PostMapping("/{id}/products")
    public UniCategory addProductToCategory(@PathVariable int id, @RequestBody UniProduct product) {
        return uniCategoryService.addProductToCategory(id, product);
    }
}
