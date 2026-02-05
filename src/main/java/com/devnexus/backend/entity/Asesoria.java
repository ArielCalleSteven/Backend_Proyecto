package com.devnexus.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "asesorias")
public class Asesoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date; 
    private String time; 

    @Column(length = 500)
    private String comment; 

    private String status; 
    
    private String programmerResponse; 

    @ManyToOne
    @JoinColumn(name = "programador_id")
    private Usuario programador;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Usuario estudiante;
}