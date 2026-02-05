package com.devnexus.backend.service;

import com.devnexus.backend.dto.HorarioDTO;
import com.devnexus.backend.entity.Horario;
import com.devnexus.backend.entity.Usuario;
import com.devnexus.backend.repository.HorarioRepository;
import com.devnexus.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Horario crearHorario(HorarioDTO dto) {
        Usuario programador = usuarioRepository.findById(dto.getProgramadorId())
                .orElseThrow(() -> new RuntimeException("Programador no encontrado"));
        
        Horario horario = new Horario();
        horario.setDay(dto.getDay());
        horario.setStart(dto.getStart());
        horario.setEnd(dto.getEnd());
        horario.setProgramador(programador);
        
        return horarioRepository.save(horario);
    }

    public List<Horario> obtenerHorariosPorProgramador(Long programadorId) {
        return horarioRepository.findByProgramadorId(programadorId);
    }
}