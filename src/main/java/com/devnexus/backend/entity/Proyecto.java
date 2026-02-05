package com.devnexus.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;       
    private String description; 
    private String category;    
    private String role;       
    
    @Column(length = 500)
    private String repo;        
    
    @Column(length = 500)
    private String demo;        

    private String tech;        
    @ManyToOne
    @JoinColumn(name = "programador_id")
    private Usuario programador;
}