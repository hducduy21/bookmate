package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private UUID id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String gender;
    private String dob;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;
    @OneToMany(mappedBy = "user")
    private Set<Review> reviews;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public Role role;
    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserProduct> userProducts = new HashSet<>();

    public User(String name, String username, String email, String password, Role role) {
        super();
        this.id = UUID.randomUUID();
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
