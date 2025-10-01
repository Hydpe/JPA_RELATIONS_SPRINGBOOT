package com.thinkrestdemo.learning.repository;

import com.thinkrestdemo.learning.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
