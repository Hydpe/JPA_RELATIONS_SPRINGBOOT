package com.thinkrestdemo.learning;

import com.thinkrestdemo.learning.models.Category;
import com.thinkrestdemo.learning.models.Product;
import com.thinkrestdemo.learning.repository.CategoryRepo;
import com.thinkrestdemo.learning.repository.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LearningApplication {
	public static void main(String[] args) {
        SpringApplication.run(LearningApplication.class, args);
    }

}
