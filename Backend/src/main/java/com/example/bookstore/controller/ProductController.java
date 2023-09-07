package com.example.bookstore.controller;

import com.example.bookstore.dto.ProductDTO;
import com.example.bookstore.iservice.ICategoryService;
import com.example.bookstore.iservice.IProductService;
import com.example.bookstore.iservice.IUserProductService;
import com.example.bookstore.iservice.IUserService;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.Product;
import com.example.bookstore.model.User;
import com.example.bookstore.model.UserProduct;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IUserProductService userProductService;
    @Value("${uploadDir}")
    private String uploadDir;

//    Hiển thị chi tiết sản phẩm
    @GetMapping(value = "/detail-product/{id}")
    public ResponseEntity<Map<String, Object>> detailProduct(@PathVariable("id") Integer id, HttpSession session) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        if (session != null || session.getAttribute("username") != null) {
            String username = String.valueOf(session.getAttribute("username"));
            Product product = productService.selectById(id);
            List<Category> categories = categoryService.selectAll();

            if (product != null && product.getIsSale())
                statusCode = HttpStatus.OK;
            else
                statusCode = HttpStatus.NO_CONTENT; // 204

            data.put("username", username);
            data.put("product", product);
            data.put("categories", categories);
        } else
            statusCode = HttpStatus.UNAUTHORIZED; // 401
        return ResponseEntity.status(statusCode).body(data);
    }

//    Xoá sản phẩm hay ngừng bán một sản phẩm (Xoá mềm)
        @GetMapping(value = "/stop-sale-product/{id}")
    public ResponseEntity<Map<String, Object>> stopSaleProduct(@PathVariable("id") Integer id, HttpSession session) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        if (session != null || session.getAttribute("username") != null) {
            String username = String.valueOf(session.getAttribute("username"));
            List<Category> categories = categoryService.selectAll();
            Product productUpdate = productService.selectById(id);

            Product product = null;
            if (productUpdate != null) {
                productUpdate.setIsSale(false);
                product = productService.update(productUpdate);

                if (product != null)
                    statusCode = HttpStatus.OK;
                else
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

//    Tìm kiếm các sản phẩm khi nhập keyword vào ô tìm kiếm
    @GetMapping(value = "/search-products")
    public ResponseEntity<Map<String, Object>> searchProducts(@RequestParam("keyword") String keyword, HttpSession session) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        if (session != null || session.getAttribute("username") != null) {
            String username = String.valueOf(session.getAttribute("username"));
            List<Category> categories = categoryService.selectAll();
            List<Product> allSearchProducts = productService.searchProductByKeyword(keyword);
            List<Product> products = getProductsOnSale(allSearchProducts);

            if (!products.isEmpty())
                statusCode = HttpStatus.OK;
            else
                statusCode = HttpStatus.NO_CONTENT;
            data.put("username", username);
            data.put("products", products);
            data.put("categories", categories);
        } else
            statusCode = HttpStatus.UNAUTHORIZED; // 401

        return ResponseEntity.status(statusCode).body(data);
    }

//    Xử lý edit
    @PostMapping(value = "/handle-edit-product/{id}")
    public ResponseEntity<Map<String, Object>> handleEditProduct(@PathVariable("id") Integer id, @RequestBody ProductDTO productDTO, HttpSession session) throws IOException {
        Map<String, Object> data = new HashMap<>();
        HttpStatus statusCode;

        if (session != null || session.getAttribute("username") != null) {
            Product productUpdate = productService.selectById(id);
            Product product = null;

            if (productUpdate != null) {
//                Xử lý ảnh
                MultipartFile file = productDTO.getFile();
                if (file != null) {
                    String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                    Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
                    Path filePath = uploadPath.resolve(fileName);

                    if (!Files.exists(uploadPath))
                        Files.createDirectories(uploadPath);

                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    productUpdate.setIamge(fileName);
                }

                productUpdate.setName(productDTO.getName());
                productUpdate.setType(productDTO.getType());
                productUpdate.setPrice(productUpdate.getPrice());
                productUpdate.setTitle(productDTO.getTitle());
                productUpdate.setPublisher(productUpdate.getPublisher());
                productUpdate.setPublicationDate(productDTO.getPublicationDate());
                productUpdate.setManufacturerName(productDTO.getManufacturerName());
                productUpdate.setIsSale(productDTO.getIsSale());

                product = productService.update(productUpdate);
                if (product != null)
                    statusCode = HttpStatus.OK;
                else
                    statusCode = HttpStatus.SERVICE_UNAVAILABLE; // 503
            }
            else
                statusCode = HttpStatus.NO_CONTENT;

            data.put("product", product);
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
