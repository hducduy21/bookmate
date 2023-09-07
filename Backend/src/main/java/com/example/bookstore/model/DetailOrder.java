package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "detail_orders")
public class DetailOrder {
    @Id
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Id
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    @JsonFormat(pattern = "dd/MM/yy")
    private LocalDate createAt;
    private Double price; // Gia luc mua, sản phẩm có thể bị thay đổi giá
    @Column(name = "is_pay", nullable = false, columnDefinition = "boolean default false")
    private Boolean isPay;
}
