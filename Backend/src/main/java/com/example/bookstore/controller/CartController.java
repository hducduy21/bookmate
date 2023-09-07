package com.example.bookstore.controller;

import com.example.bookstore.iservice.ICategoryService;
import com.example.bookstore.iservice.IProductService;
import com.example.bookstore.iservice.IUserProductService;
import com.example.bookstore.iservice.IUserService;
import com.example.bookstore.model.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IUserProductService userProductService;

//    Hiển thị giao diện trang cart
    @GetMapping(value = "/")
    public ResponseEntity<Map<String, Object>> cartPage(HttpSession session) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        if (session != null || session.getAttribute("username") != null) {
            String username = String.valueOf(session.getAttribute("username"));
            UUID id = UUID.fromString(String.valueOf(session.getAttribute("userId")));
            List<Product> products = userService.getProductsBought(id); // tất cả các sản phẩm người dùng đã mua
            List<Category> categories = categoryService.selectAll();


            if (!products.isEmpty())
                statusCode = HttpStatus.OK;
            else
                statusCode = HttpStatus.NO_CONTENT; // 204

            data.put("username", username);
            data.put("products", products);
            data.put("categories", categories);
        } else
            statusCode = HttpStatus.UNAUTHORIZED; // 401
        return ResponseEntity.status(statusCode).body(data);
    }

//    tăng số lượng của một sản phẩm muốn mua (add to cart)
    @GetMapping(value = "/increase-product/{id}")
    public ResponseEntity<Map<String, Object>> increaseProduct(@PathVariable("id") Integer id, HttpSession session) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        if (session != null || session.getAttribute("username") != null) {
            UUID userId = UUID.fromString(String.valueOf(session.getAttribute("userId")));
            User user = userService.selectById(userId);
            String username = String.valueOf(session.getAttribute("username"));
            Product product = productService.selectById(id); // Sản phẩm muốn tăng
            List<Category> categories = categoryService.selectAll();

            if (product != null) {
                UserProduct userProduct = userService.increaseProductQuantitiesForUser(user, product);
                if (userProduct != null) {
                    userProductService.insert(userProduct);
                    statusCode = HttpStatus.OK;
                } else
                    statusCode = HttpStatus.SERVICE_UNAVAILABLE; // 503
            } else
                statusCode = HttpStatus.NO_CONTENT; // 204
            data.put("username", username);
            data.put("product", product);
            data.put("categories", categories);
        } else
            statusCode = HttpStatus.UNAUTHORIZED; // 401

        return ResponseEntity.status(statusCode).body(data);
    }

//    giảm số lượng của một sản phẩm muốn mua (add to cart)
    @GetMapping(value = "/decrease-product/{id}")
    public ResponseEntity<Map<String, Object>> decreaseProduct(@PathVariable("id") Integer id, HttpSession session) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        if (session != null || session.getAttribute("username") != null) {
            UUID userId = UUID.fromString(String.valueOf(session.getAttribute("userId")));
            User user = userService.selectById(userId);
            String username = String.valueOf(session.getAttribute("username"));
            List<Category> categories = categoryService.selectAll();
            Product product = productService.selectById(id); // Sản phẩm muốn giảm

            if (product != null) {
                UserProduct userProduct = userService.decreaseProductQuantitiesForUser(user, product);
                if (userProduct != null) {
                    userProductService.insert(userProduct);
                    statusCode = HttpStatus.OK;
                } else
                    statusCode = HttpStatus.SERVICE_UNAVAILABLE; // 503
            } else
                statusCode = HttpStatus.NO_CONTENT; // 204
            data.put("username", username);
            data.put("product", product);
            data.put("categories", categories);
        } else
            statusCode = HttpStatus.UNAUTHORIZED; // 401

        return ResponseEntity.status(statusCode).body(data);
    }

//    Xoá sản phẩm ra khỏi giỏ hàng
    @GetMapping(value = "/remove-product/{id}")
    public ResponseEntity<Map<String, Object>> removeProductFromCart(@PathVariable("id") Integer id, HttpSession session) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        if (session != null || session.getAttribute("username") != null) {
            UUID userId = UUID.fromString(String.valueOf(session.getAttribute("userId")));
            User user = userService.selectById(userId);
            String username = String.valueOf(session.getAttribute("username")); // return dta
            List<Category> categories = categoryService.selectAll(); // return data
            Product product = productService.selectById(id); // Sản phẩm muốn remove

            if (product != null) {
                boolean isRemove = userProductService.deleteById(new UserProductId(user, product));
                if (isRemove)
                    statusCode = HttpStatus.OK;
                else
                    statusCode = HttpStatus.NO_CONTENT; // 204
            } else
                statusCode = HttpStatus.NO_CONTENT; // 204
            data.put("username", username);
            data.put("product", product);
            data.put("categories", categories);
        } else
            statusCode = HttpStatus.UNAUTHORIZED; // 401

        return ResponseEntity.status(statusCode).body(data);
    }

//    Lấy các products còn bán
    private List<Product> getProductsOnSale(List<Product> allProduct) {
        List<Product> products = new ArrayList<>();
        if (allProduct != null) {
            for (Product product : allProduct) {
                if (product.getIsSale())
                    products.add(product);
            }
        }
        return products;
    }
}
