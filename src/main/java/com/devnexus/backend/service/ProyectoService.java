package com.devnexus.backend.service;

import com.devnexus.backend.dto.ProyectoDTO;
import com.devnexus.backend.entity.Proyecto;
import com.devnexus.backend.entity.Usuario;
import com.devnexus.backend.repository.ProyectoRepository;
import com.devnexus.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Proyecto> listarPorProgramador(Long programadorId) {
        return proyectoRepository.findByProgramadorId(programadorId);
    }

    public Proyecto guardarProyecto(ProyectoDTO dto) {
        Usuario programador = usuarioRepository.findById(dto.getProgramadorId())
                .orElseThrow(() -> new RuntimeException("Programador no encontrado"));

        Proyecto proyecto = new Proyecto();
        return mapAndSave(proyecto, dto, programador);
    }

    public Proyecto actualizarProyecto(Long id, ProyectoDTO dto) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
        
        return mapAndSave(proyecto, dto, proyecto.getProgramador());
    }

    public void eliminarProyecto(Long id) {
        proyectoRepository.deleteById(id);
    }

    private Proyecto mapAndSave(Proyecto proyecto, ProyectoDTO dto, Usuario programador) {
        proyecto.setTitle(dto.getTitle());
        proyecto.setDescription(dto.getDescription());
        proyecto.setCategory(dto.getCategory());
        proyecto.setRole(dto.getRole());         
        proyecto.setRepo(dto.getRepo());
        proyecto.setDemo(dto.getDemo());
        proyecto.setTech(dto.getTech());        
        proyecto.setProgramador(programador);
        return proyectoRepository.save(proyecto);
    }
}