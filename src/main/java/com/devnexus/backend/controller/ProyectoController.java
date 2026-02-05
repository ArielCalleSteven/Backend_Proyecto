package com.devnexus.backend.controller;

import com.devnexus.backend.dto.ProyectoDTO;
import com.devnexus.backend.entity.Proyecto;
import com.devnexus.backend.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@CrossOrigin(origins = "*")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping("/programador/{id}")
    public List<Proyecto> listar(@PathVariable Long id) {
        return proyectoService.listarPorProgramador(id);
    }

    @PostMapping("/crear")
    public Proyecto crear(@RequestBody ProyectoDTO dto) {
        return proyectoService.guardarProyecto(dto);
    }

    @PutMapping("/{id}")
    public Proyecto actualizar(@PathVariable Long id, @RequestBody ProyectoDTO dto) {
        return proyectoService.actualizarProyecto(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
        return ResponseEntity.noContent().build();
    }
}