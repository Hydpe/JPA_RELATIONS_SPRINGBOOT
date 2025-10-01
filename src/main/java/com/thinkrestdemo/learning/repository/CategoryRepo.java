package com.thinkrestdemo.learning.repository;

import com.thinkrestdemo.learning.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,String> {
}
