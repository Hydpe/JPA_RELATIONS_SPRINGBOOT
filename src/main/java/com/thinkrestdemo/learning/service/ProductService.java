package com.thinkrestdemo.learning.service;

import com.thinkrestdemo.learning.models.Category;
import com.thinkrestdemo.learning.models.Product;
import com.thinkrestdemo.learning.repository.CategoryRepo;
import com.thinkrestdemo.learning.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;
    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }



    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }


    public Optional<Product> getProductById(long id) {
        return productRepo.findById(id);
    }




    public void deleteProduct(long id) {
        Optional<Product> productOpt = productRepo.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();

            for (Category cat : product.getCategories()) {
                cat.getProducts().remove(product);
                categoryRepo.save(cat);
            }
            productRepo.delete(product);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }
}