package com.devnexus.backend.service;

import com.devnexus.backend.dto.UsuarioDTO;
import com.devnexus.backend.entity.Usuario;
import com.devnexus.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario loginOcrearConGoogle(String email, String name) {
        Optional<Usuario> existente = usuarioRepository.findByEmail(email);
        
        if (existente.isPresent()) {
            return existente.get();
        } else {
            Usuario nuevo = new Usuario();
            nuevo.setEmail(email);
            nuevo.setName(name != null ? name : "Usuario Google");
            nuevo.setRole("student"); 
            nuevo.setPassword("GOOGLE_AUTH_USER"); 
            nuevo.setDescription("Estudiante registrado vía Google");
            return usuarioRepository.save(nuevo);
        }
    }

    public Usuario registrar(Usuario usuario) {
        if (usuario.getRole() == null || usuario.getRole().isEmpty()) {
            usuario.setRole("student");
        }
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> listarSoloProgramadores() {
        return usuarioRepository.findByRole("programmer");
    }

    public Usuario actualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if(dto.getName() != null) usuario.setName(dto.getName());
        if(dto.getDescription() != null) usuario.setDescription(dto.getDescription());
        if(dto.getPhotoUrl() != null) usuario.setPhotoUrl(dto.getPhotoUrl());
        if(dto.getGithub() != null) usuario.setGithub(dto.getGithub());
        if(dto.getLinkedin() != null) usuario.setLinkedin(dto.getLinkedin());
        if(dto.getRole() != null) usuario.setRole(dto.getRole());

        if(dto.getAvailability() != null) {
            usuario.setAvailability(dto.getAvailability());
        }

        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }
}