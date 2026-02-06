package com.devnexus.backend.entity;

import jakarta.persistence.*;

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

    // 👇 AQUÍ ESTÁ LA CLAVE. Lo forzamos a buscar la columna "role"
    @Column(name = "role") 
    private String role; 

    @Column(length = 1000)
    private String photoUrl; 

    @Column(length = 500)
    private String description; 

    private String github;  
    private String linkedin; 

    @Column(columnDefinition = "TEXT")
    private String availability;



    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getGithub() { return github; }
    public void setGithub(String github) { this.github = github; }

    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }
}