package com.example.bookstore.service;

import com.example.bookstore.iservice.IUserProductService;
import com.example.bookstore.model.Product;
import com.example.bookstore.model.User;
import com.example.bookstore.model.UserProduct;
import com.example.bookstore.model.UserProductId;
import com.example.bookstore.repository.UserProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserProductService implements IUserProductService {
    @Autowired
    private UserProductRepository userProductRepository;

    @Override
    public List<UserProduct> selectAll() {
        return userProductRepository.findAll();
    }

    @Override
    public UserProduct selectById(UserProductId userProductId) {
        return userProductRepository.findById(userProductId).orElse(null);
    }

    @Override
    public UserProduct insert(UserProduct userProduct) {
        return userProductRepository.save(userProduct);
    }

    @Override
    public boolean deleteById(UserProductId userProductId) {
        if (userProductRepository.existsById(userProductId))
            userProductRepository.deleteById(userProductId);
        return !userProductRepository.existsById(userProductId);
    }

    @Override
    public UserProduct update(UserProduct userProductUpdate) {
        return userProductRepository.save(userProductUpdate);
    }

    @Override
    public List<UserProduct> findByUser(User user) {
        return userProductRepository.findByUser(user);
    }

    @Override
    public List<UserProduct> findByProduct(Product product) {
        return userProductRepository.findByProduct(product);
    }

    @Override
    public UserProduct findByUserAndProduct(User user, Product product) {
        return userProductRepository.findByUserAndProduct(user, product);
    }
}
