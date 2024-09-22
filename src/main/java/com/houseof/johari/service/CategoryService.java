package com.houseof.johari.service;

import com.houseof.johari.model.Category;
import com.houseof.johari.repository.CategoryRepository;
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

    public Category getCategoryById(String id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // More services for categories if needed
}
