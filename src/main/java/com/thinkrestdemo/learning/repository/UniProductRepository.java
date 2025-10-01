package com.thinkrestdemo.learning.repository;

import com.thinkrestdemo.learning.models.UniProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniProductRepository extends JpaRepository<UniProduct,Long> {
}
