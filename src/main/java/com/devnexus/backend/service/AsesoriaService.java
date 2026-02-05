package com.devnexus.backend.service;

import com.devnexus.backend.dto.AsesoriaDTO;
import com.devnexus.backend.entity.Asesoria;
import com.devnexus.backend.entity.Usuario;
import com.devnexus.backend.repository.AsesoriaRepository;
import com.devnexus.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsesoriaService {

    @Autowired
    private AsesoriaRepository asesoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Asesoria guardarAsesoria(AsesoriaDTO dto) {
        Asesoria asesoria = new Asesoria();
        
        Usuario programador = usuarioRepository.findById(Long.valueOf(dto.getProgrammerId()))
                .orElseThrow(() -> new RuntimeException("Programador no encontrado"));
        
        Usuario estudiante = usuarioRepository.findByEmail(dto.getClientEmail())
                .orElseThrow(() -> new RuntimeException("Estudiante con email " + dto.getClientEmail() + " no encontrado"));

        asesoria.setDate(dto.getDate());
        asesoria.setTime(dto.getTime());
        asesoria.setComment(dto.getTopic()); 
        asesoria.setStatus("PENDIENTE"); 
        asesoria.setProgramador(programador);
        asesoria.setEstudiante(estudiante);

        return asesoriaRepository.save(asesoria);
    }

    public Asesoria responderSolicitud(Long id, String estado, String respuesta) {
        Asesoria asesoria = asesoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        asesoria.setStatus(estado);
        asesoria.setProgrammerResponse(respuesta); 

        return asesoriaRepository.save(asesoria);
    }

    public List<AsesoriaDTO> listarPorEmailEstudiante(String email) {
        List<Asesoria> asesorias = asesoriaRepository.findByEstudianteEmail(email);
        return asesorias.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public List<AsesoriaDTO> listarPorProgramador(Long programadorId) {
        List<Asesoria> asesorias = asesoriaRepository.findByProgramadorId(programadorId);
        return asesorias.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    private AsesoriaDTO convertirADTO(Asesoria asesoria) {
        AsesoriaDTO dto = new AsesoriaDTO();
        
        dto.setId(String.valueOf(asesoria.getId()));
        dto.setProgrammerId(String.valueOf(asesoria.getProgramador().getId()));
        
        dto.setProgrammerName(asesoria.getProgramador().getName());
        dto.setClientName(asesoria.getEstudiante().getName());
        dto.setClientEmail(asesoria.getEstudiante().getEmail()); 
        
        dto.setDate(asesoria.getDate());
        dto.setTime(asesoria.getTime());
        dto.setTopic(asesoria.getComment()); 
        dto.setStatus(asesoria.getStatus());
        dto.setResponseMessage(asesoria.getProgrammerResponse()); 
        
        return dto;
    }

    public void eliminarAsesoria(Long id) {
        asesoriaRepository.deleteById(id);
    }
}