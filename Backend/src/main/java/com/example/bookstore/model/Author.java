package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Table(name = "authors")
public class Author {
    @Id
    private UUID id;
    private String name;
    @JsonFormat(pattern = "dd/MM/yy")
    private LocalDate dob;
    private String description;

    @OneToMany(mappedBy = "author")
    private Set<Product> products = new HashSet<>();

    public Author() {
        super();
        this.id = UUID.randomUUID();
    }

    public Author(String name, LocalDate dob, String description) {
        super();
        this.id = UUID.randomUUID();
        this.name = name;
        this.dob = dob;
        this.description = description;
    }
}
