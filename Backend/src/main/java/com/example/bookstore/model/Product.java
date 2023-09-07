package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @NonNull
    private Integer type;
    @Column(name = "quantity", nullable = false, columnDefinition = "int default 1")
    private Integer quantity;
    private Double price;
    private String title;
    private String publisher;
    @JsonFormat(pattern = "dd/MM/yy")
    private LocalDate publicationDate;
    private String manufacturerName;
    private String iamge;
    private Boolean isSale = true;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<DetailOrder> detailOrders = new HashSet<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();
    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductCategory> productCategories = new HashSet<>();
    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<UserProduct> userProducts = new HashSet<>();

    public Product(String name, @NonNull Integer type, int quantity, Double price, String title,
                   String publisher, LocalDate publicationDate, String manufacturerName, @NonNull Author author) {
        super();
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.title = title;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.manufacturerName = manufacturerName;
        this.author = author;
    }
}
