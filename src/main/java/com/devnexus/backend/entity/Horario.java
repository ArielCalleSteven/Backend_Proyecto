package com.devnexus.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String day;   
    private String start; 
    private String end;   

    @ManyToOne
    @JoinColumn(name = "programador_id")
    private Usuario programador;
}