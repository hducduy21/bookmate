package com.example.bookstore.iservice;

import com.example.bookstore.model.Product;
import com.example.bookstore.model.User;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    List<Product> selectAll();
    Product selectById(Integer id);
    Product insert(Product product);
    boolean deleteById(Integer id);
    Product update(Product productUpdate);
    List<Product> searchProductByKeyword(String keyword);
}
