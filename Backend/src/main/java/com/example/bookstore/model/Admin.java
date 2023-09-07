package com.example.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admins")
public class Admin {
    @Id
    private UUID adminId;
    private String username;
    private String adminPassword;

    public Admin(String username, String adminPassword) {
        super();
        this.adminId = UUID.randomUUID();
        this.username = username;
        this.adminPassword = adminPassword;
    }
}
