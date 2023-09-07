package com.example.bookstore.iservice;

import com.example.bookstore.model.Product;
import com.example.bookstore.model.User;
import com.example.bookstore.model.UserProduct;
import com.example.bookstore.model.UserProductId;

import java.util.List;
import java.util.UUID;

public interface IUserProductService {
    List<UserProduct> selectAll();
    UserProduct selectById(UserProductId userProductId);
    UserProduct insert(UserProduct userProduct);
    boolean deleteById(UserProductId userProductId);
    UserProduct update(UserProduct userProductUpdate);
    List<UserProduct> findByUser(User user);
    List<UserProduct> findByProduct(Product product);
    UserProduct findByUserAndProduct(User user, Product product);
}
