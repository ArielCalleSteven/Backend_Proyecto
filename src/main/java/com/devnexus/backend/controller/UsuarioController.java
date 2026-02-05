package com.devnexus.backend.controller;

import com.devnexus.backend.dto.UsuarioDTO;
import com.devnexus.backend.entity.Usuario;
import com.devnexus.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO loginDto) {
        Optional<Usuario> user = usuarioService.buscarPorEmail(loginDto.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(loginDto.getPassword())) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }

    @PostMapping("/google")
    public ResponseEntity<?> loginGoogle(@RequestBody UsuarioDTO googleDto) {
        Usuario user = usuarioService.loginOcrearConGoogle(googleDto.getEmail(), googleDto.getName());
        return ResponseEntity.ok(user);
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
}