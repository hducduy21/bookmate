package com.example.bookstore.service;

import com.example.bookstore.iservice.ICategoryService;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.Product;
import com.example.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> selectAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category selectById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category insert(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public boolean deleteById(Integer id) {
        if (categoryRepository.existsById(id))
            categoryRepository.deleteById(id);
        return !categoryRepository.existsById(id);
    }

    @Override
    public Category update(Category categoryUpdate) {
        return categoryRepository.save(categoryUpdate);
    }

    @Override
    public List<Product> getProductsByCategory(Integer id) {
        return categoryRepository.getProductsByCategory(id);
    }
}
