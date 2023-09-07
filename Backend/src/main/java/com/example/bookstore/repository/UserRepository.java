package com.example.bookstore.repository;

import com.example.bookstore.model.Product;
import com.example.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
    @Query(nativeQuery = true,
            value = "SELECT p FROM products p " +
                    "INNER JOIN user_products up ON p.id = up.product_id " +
                    "INNER JOIN users u ON u.id = up.user_id " +
                    "WHERE u.id = :id")
    List<Product> getProductsBought(UUID id);
}
