package com.thinkrestdemo.learning.repository;

import com.thinkrestdemo.learning.models.UniCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UniCategoryRepository extends JpaRepository<UniCategory,Integer> {
}
