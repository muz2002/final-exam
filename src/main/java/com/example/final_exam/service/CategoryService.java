package com.example.final_exam.service;
import com.example.final_exam.entity.Category;
import com.example.final_exam.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category c) {
        return categoryRepository.save(c);
    }

    public Category updateCategory(Long id, Category c) {
        Category existing = categoryRepository.findById(id).orElseThrow();
        existing.setName(c.getName());
        existing.setDescription(c.getDescription());
        return categoryRepository.save(existing);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}

