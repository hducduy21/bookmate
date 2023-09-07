package com.example.bookstore.service;

import com.example.bookstore.iservice.IProductService;
import com.example.bookstore.iservice.IUserService;
import com.example.bookstore.model.Product;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productService;
    @Override
    public List<Product> selectAll() {
        return productService.findAll();
    }

    @Override
    public Product selectById(Integer id) {
        return productService.findById(id).orElse(null);
    }

    @Override
    public Product insert(Product product) {
        return productService.save(product);
    }

    @Override
    public boolean deleteById(Integer id) {
        if (productService.existsById(id))
            productService.deleteById(id);
        return !productService.existsById(id);
    }

    @Override
    public Product update(Product productUpdate) {
        return productService.save(productUpdate);
    }

    @Override
    public List<Product> searchProductByKeyword(String keyword) {
        List<Product> products = new ArrayList<>();
        List<Product> searchProductsByString = productService.searchProductsByString(keyword);

        double priceKey = Double.parseDouble(keyword);
        Double minPrice = priceKey - 100;
        Double maxPrice = priceKey + 100;
        List<Product> searchByPrice = productService.findByPriceBetween(minPrice, maxPrice);

        Integer id = Integer.parseInt(keyword);
        Product searchById = selectById(id);

        if (searchProductsByString != null && !searchProductsByString.isEmpty()) {
            products.addAll(searchProductsByString);
        }

        if (searchByPrice != null && !searchByPrice.isEmpty()) {
            products.addAll(searchByPrice);
        }

        if (searchById != null) {
            products.add(searchById);
        }
        return products;
    }
}
