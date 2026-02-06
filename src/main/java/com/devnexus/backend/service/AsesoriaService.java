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

    @Autowired
    private EmailService emailService;

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

        Asesoria guardada = asesoriaRepository.save(asesoria);

        try {
            String asuntoProg = "🔔 Nueva Solicitud de Asesoría - Portafolio";
            String mensajeProg = "Hola " + programador.getName() + ",\n\n" +
                                 "Tienes una nueva solicitud de asesoría pendiente.\n" +
                                 "Estudiante: " + estudiante.getName() + "\n" +
                                 "Tema: " + dto.getTopic() + "\n" +
                                 "Fecha: " + dto.getDate() + " a las " + dto.getTime() + "\n\n" +
                                 "Ingresa a tu panel para aceptarla o rechazarla.";
            emailService.sendEmail(programador.getEmail(), asuntoProg, mensajeProg);

            String asuntoEst = "✅ Solicitud Enviada Exitosamente";
            String mensajeEst = "Hola " + estudiante.getName() + ",\n\n" +
                                "Tu solicitud de asesoría con " + programador.getName() + " ha sido enviada.\n" +
                                "Estado actual: PENDIENTE.\n" +
                                "Te notificaremos por correo cuando el programador responda.";
            emailService.sendEmail(estudiante.getEmail(), asuntoEst, mensajeEst);

        } catch (Exception e) {
            System.err.println("⚠️ No se pudo enviar el correo (pero la cita se guardó): " + e.getMessage());
        }

        return guardada;
    }

    public Asesoria responderSolicitud(Long id, String estado, String respuesta) {
        Asesoria asesoria = asesoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        asesoria.setStatus(estado);
        asesoria.setProgrammerResponse(respuesta); 

        Asesoria actualizada = asesoriaRepository.save(asesoria);

        try {
            String asunto = "📢 Actualización de tu Asesoría: " + estado;
            String mensaje = "Hola " + asesoria.getEstudiante().getName() + ",\n\n" +
                             "El estado de tu asesoría con " + asesoria.getProgramador().getName() + " ha cambiado.\n" +
                             "NUEVO ESTADO: " + estado + "\n\n" +
                             "Mensaje del programador: " + respuesta;
            
            emailService.sendEmail(asesoria.getEstudiante().getEmail(), asunto, mensaje);

        } catch (Exception e) {
            System.err.println("⚠️ No se pudo enviar el correo de respuesta: " + e.getMessage());
        }

        return actualizada;
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