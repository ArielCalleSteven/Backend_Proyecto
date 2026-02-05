package com.devnexus.backend.dto;

import lombok.Data;

@Data
public class ProyectoDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String role;
    private String repo;
    private String demo;
    private String tech;
    private Long programadorId;
}