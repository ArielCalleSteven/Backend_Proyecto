package com.devnexus.backend.controller;

import com.devnexus.backend.dto.UsuarioDTO;
import com.devnexus.backend.entity.Usuario;
import com.devnexus.backend.service.UsuarioService;
import com.devnexus.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = {"http://localhost:4200", "https://portafolio-calle-torres-2025.web.app"})
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO loginDto) {
        Optional<Usuario> userOpt = usuarioService.buscarPorEmail(loginDto.getEmail());
        
        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();
            if (user.getPassword().equals(loginDto.getPassword())) {
                
                String token = jwtUtil.generateToken(user.getEmail());
                
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("usuario", user);
                
                response.put("role", user.getRole() != null ? user.getRole() : "student");
                
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }

    @PostMapping("/google")
    public ResponseEntity<?> loginGoogle(@RequestBody UsuarioDTO googleDto) {
        Usuario user = usuarioService.loginOcrearConGoogle(googleDto.getEmail(), googleDto.getName());
        
        String token = jwtUtil.generateToken(user.getEmail());
        
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("usuario", user);
        
        // 🔥 FIX OBLIGATORIO: FORZAMOS EL ROL AQUÍ TAMBIÉN
        response.put("role", user.getRole() != null ? user.getRole() : "student");
        
        return ResponseEntity.ok(response);
    }


    @PostMapping("/registro")
    public Usuario registrar(@RequestBody Usuario usuario) {
        return usuarioService.registrar(usuario);
    }

    @GetMapping("/programadores")
    public List<Usuario> listarProgramadores() {
        return usuarioService.listarSoloProgramadores(); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        Usuario actualizado = usuarioService.actualizarUsuario(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build(); 
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // COMENTARIO PARA FORZAR ACTUALIZACIÓN EN RAILWAY - V2
}