package com.example.bookstore.repository;

import com.example.bookstore.model.Product;
import com.example.bookstore.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.title LIKE %:keyword% OR p.publisher LIKE %:keyword% " +
            "OR p.manufacturerName LIKE %:keyword%")
    List<Product> searchProductsByString(@Param("keyword") String keyword);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
}
