package com.devnexus.backend.controller;

import com.devnexus.backend.dto.HorarioDTO;
import com.devnexus.backend.entity.Horario;
import com.devnexus.backend.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@CrossOrigin(origins = "*")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @PostMapping("/crear")
    public Horario crear(@RequestBody HorarioDTO dto) {
        return horarioService.crearHorario(dto);
    }

    @GetMapping("/programador/{id}")
    public List<Horario> listar(@PathVariable Long id) {
        return horarioService.obtenerHorariosPorProgramador(id);
    }
}