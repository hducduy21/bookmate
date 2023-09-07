package com.example.bookstore.iservice;

import com.example.bookstore.model.Category;
import com.example.bookstore.model.Product;

import java.util.List;

public interface ICategoryService {
    List<Category> selectAll();
    Category selectById(Integer id);
    Category insert(Category category);
    boolean deleteById(Integer id);
    Category update(Category categoryUpdate);
    List<Product> getProductsByCategory(Integer id);
}
