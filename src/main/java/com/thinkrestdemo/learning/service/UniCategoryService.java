package com.thinkrestdemo.learning.service;

import com.thinkrestdemo.learning.models.UniCategory;
import com.thinkrestdemo.learning.models.UniProduct;
import com.thinkrestdemo.learning.repository.UniCategoryRepository;
import com.thinkrestdemo.learning.repository.UniProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UniCategoryService {

    private final UniCategoryRepository uniCategoryRepository;
    private final UniProductRepository uniProductRepository;


    @Autowired
    public UniCategoryService(UniCategoryRepository uniCategoryRepository,
                              UniProductRepository uniProductRepository) {
        this.uniCategoryRepository = uniCategoryRepository;
        this.uniProductRepository = uniProductRepository;
    }


    public UniCategory createCategory(UniCategory category) {


        return uniCategoryRepository.save(category);
    }


    public List<UniCategory> getAllCategories() {
        return uniCategoryRepository.findAll();
    }

    public Optional<UniCategory> getCategoryById(int id) {
        return uniCategoryRepository.findById(id);
    }

    public UniCategory updateCategory(int id, UniCategory updatedCategory)
    {
        UniCategory uniCategory = uniCategoryRepository.findById(id).get();

        if(!(uniCategory!=null))
        {
            throw new RuntimeException("Category not found: "+id);
        }

        uniCategory.setCategory(updatedCategory.getCategory());

        if(updatedCategory.getCategory()!=null)

        uniCategory.getProducts().addAll(updatedCategory.getProducts());
        return uniCategoryRepository.save(uniCategory);
    }

    public void deleteCategory(int id) {
        if (!uniCategoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        uniCategoryRepository.deleteById(id);
    }

    public UniCategory addProductToCategory(int categoryId, UniProduct product) {
        UniCategory category = uniCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        if (product.getPid() != null) {

            uniProductRepository.save(product);
        }

        category.addProduct(product);
        return uniCategoryRepository.save(category);
    }
}
