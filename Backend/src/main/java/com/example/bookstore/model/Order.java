package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @JsonFormat(pattern = "dd/MM/yy")
    private LocalDate createAt;
    private String cost;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<DetailOrder> detailOrders = new HashSet<>();


    public Order(LocalDate createAt, String cost, @NonNull User user) {
        super();
        this.createAt = createAt;
        this.cost = cost;
        this.user = user;
    }
}
