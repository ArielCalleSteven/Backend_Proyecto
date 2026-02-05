package com.devnexus.backend.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String email;
    private String password; 
    private String name;
    private String role;     
    
    private String photoUrl;
    private String description;
    private String github;
    private String linkedin;

    private String availability;
}