package com.devnexus.backend.controller;

import com.devnexus.backend.dto.AsesoriaDTO;
import com.devnexus.backend.entity.Asesoria;
import com.devnexus.backend.service.AsesoriaService;
import com.devnexus.backend.service.PdfService; // <--- IMPORTANTE: Importamos el servicio de PDF
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource; // <--- NUEVO
import org.springframework.http.HttpHeaders; // <--- NUEVO
import org.springframework.http.MediaType; // <--- NUEVO
import org.springframework.http.ResponseEntity; // <--- NUEVO
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/asesorias")
@CrossOrigin(origins = "http://localhost:4200")
public class AsesoriaController {

    @Autowired
    private AsesoriaService asesoriaService;

    // 👇 INYECCIÓN NUEVA PARA EL PDF 👇
    @Autowired
    private PdfService pdfService;

    @PostMapping
    public Asesoria solicitarAsesoria(@RequestBody AsesoriaDTO dto) {
        return asesoriaService.guardarAsesoria(dto);
    }

    @GetMapping("/estudiante/email/{email}")
    public List<AsesoriaDTO> listarPorEmail(@PathVariable String email) {
        return asesoriaService.listarPorEmailEstudiante(email);
    }

    @GetMapping("/programador/{id}")
    public List<AsesoriaDTO> listarPorProgramador(@PathVariable Long id) {
        return asesoriaService.listarPorProgramador(id);
    }

    @PatchMapping("/{id}/responder")
    public Asesoria responder(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String estado = payload.get("status");    
        String respuesta = payload.get("programmerResponse"); 
        return asesoriaService.responderSolicitud(id, estado, respuesta);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        asesoriaService.eliminarAsesoria(id);
    }

    @GetMapping("/{id}/reporte-pdf")
    public ResponseEntity<InputStreamResource> descargarReporte(@PathVariable Long id) {
        List<AsesoriaDTO> lista = asesoriaService.listarPorProgramador(id);
        
        ByteArrayInputStream bis = pdfService.generarReporteAsesorias(lista);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=reporte_asesorias.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}