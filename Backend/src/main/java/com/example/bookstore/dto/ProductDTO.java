package com.example.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private MultipartFile file;
    private String name;
    private Integer type;
    private Double price;
    private String title;
    private String publisher;
    @JsonFormat(pattern = "dd/MM/yy")
    private LocalDate publicationDate;
    private String manufacturerName;
    private Boolean isSale = true;
}
