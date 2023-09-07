package com.example.bookstore.repository;

import com.example.bookstore.model.Category;
import com.example.bookstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT p FROM products p " +
                    "INNER JOIN product_category pc ON p.id = pc.product_id " +
                    "INNER JOIN categories c ON c.id = pc.category_id " +
                    "WHERE c.id = :id")
    List<Product> getProductsByCategory(@Param("id") Integer category_id);
}
