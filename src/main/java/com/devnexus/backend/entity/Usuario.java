package com.devnexus.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; 

    private String name; 

    private String role; 

    @Column(length = 1000)
    private String photoUrl; 

    @Column(length = 500)
    private String description; 

    private String github;  
    private String linkedin; 

    @Column(columnDefinition = "TEXT")
    private String availability; 
}