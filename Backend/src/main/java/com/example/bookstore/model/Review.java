package com.example.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer rating;
    private String comment;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Review(Integer rating, String comment, @NonNull Product product, @NonNull User user) {
        super();
        this.rating = rating;
        this.comment = comment;
        this.product = product;
        this.user = user;
    }
}
