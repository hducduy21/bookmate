package com.example.bookstore.controller;

import com.example.bookstore.iservice.ICategoryService;
import com.example.bookstore.iservice.IProductService;
import com.example.bookstore.iservice.IUserService;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.Product;
import com.example.bookstore.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/home")
public class HomeController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

//    Hiển thị giao diện trang home
    @GetMapping(value = "/")
    public ResponseEntity<Map<String, Object>> homepage(HttpSession session) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        if (session != null || session.getAttribute("username") != null) {
            String username = String.valueOf(session.getAttribute("username"));
            List<Product> allProduct = productService.selectAll(); // tất cả các sản phẩm có trong database
            List<Category> categories = categoryService.selectAll();

            List<Product> products = getProductsOnSale(allProduct); // Sản phẩm còn bán

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

//    Hiển thị thông tin user
    @GetMapping(value = "/my-profile")
    public ResponseEntity<Map<String, Object>> myProfile(HttpSession session) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        if (session != null || session.getAttribute("username") != null) {
            String username = String.valueOf(session.getAttribute("username"));
            List<Category> categories = categoryService.selectAll();
            User user = userService.selectByUsername(username);


            if (user != null)
                statusCode = HttpStatus.OK;
            else
                statusCode = HttpStatus.NO_CONTENT; // 204
            data.put("username", username);
            data.put("categories", categories);
            data.put("user", user);
        } else
            statusCode = HttpStatus.UNAUTHORIZED; // 401
        return ResponseEntity.status(statusCode).body(data);
    }

//    Hiển thị các sản phẩm theo category
    @GetMapping(value = "category-products/{id}")
    public ResponseEntity<Map<String, Object>> categoryProducts(@PathVariable("id") Integer id, HttpSession session) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        if (session != null || session.getAttribute("username") != null) {
            String username = String.valueOf(session.getAttribute("username"));
            List<Category> categories = categoryService.selectAll();
            List<Product> allProductByCategory = categoryService.getProductsByCategory(id);

            List<Product> products = getProductsOnSale(allProductByCategory); // Sản phẩm còn bán

            if (!products.isEmpty())
                statusCode = HttpStatus.OK;
            else
                statusCode = HttpStatus.NO_CONTENT; // 204

            data.put("username", username);
            data.put("products", products);
            data.put("categories", categories);
        } else {
            statusCode = HttpStatus.UNAUTHORIZED; // 401
        }
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
