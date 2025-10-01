package com.thinkrestdemo.learning.controller;

import com.thinkrestdemo.learning.models.Category;
import com.thinkrestdemo.learning.models.Product;
import com.thinkrestdemo.learning.repository.CategoryRepo;
import com.thinkrestdemo.learning.repository.ProductRepo;
import com.thinkrestdemo.learning.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ch.qos.logback.core.joran.JoranConstants.NULL;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ProductRepo productRepo;
    public ProductController(ProductService productService, ProductRepo productRepo,CategoryRepo categoryRepo) {
        this.productService = productService;
        this.productRepo = productRepo;
        this.categoryRepo=categoryRepo;
    }

   @PostMapping
   public Product createProduct(@RequestBody Product product)
   {
       List<Category> managedCategories = new ArrayList<>();
       for (Category c : product.getCategories()) {
           if(c.getcId()!= null && categoryRepo.existsById(c.getcId()) ) {
               Category existingCategory = categoryRepo.findById(c.getcId())
                       .orElseThrow(() -> new RuntimeException("Category not found"));
               managedCategories.add(existingCategory);
           }
           else {
               Category newCategory = new Category();
               newCategory.setcId(c.getcId());
               newCategory.setTitle(c.getTitle());
               categoryRepo.save(newCategory);
               managedCategories.add(newCategory);
           }
       }
       Product managedProduct = new Product();
      // managedProduct.setPid(product.getPid());
       managedProduct.setPname(product.getPname());
       managedProduct.setCategories(managedCategories);

       for (Category c : managedCategories) {
           c.getProducts().add(managedProduct);
       }
       return productRepo.save(managedProduct);
   }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product updatedProduct) {
        return productRepo.findById(id).map(product -> {
            product.setPname(updatedProduct.getPname());

            List<Category> managedCategories = new ArrayList<>();
            for(Category c : updatedProduct.getCategories())
            {
                Category existingCategory = categoryRepo.findById(c.getcId())
                        .orElseThrow(() -> new RuntimeException("Category not found"));
                 managedCategories.add(existingCategory);
            }
            product.setCategories(managedCategories);
            for(Category c : managedCategories) {
                c.getProducts().add(product);
            }
            return productRepo.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product with ID " + id + " deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}