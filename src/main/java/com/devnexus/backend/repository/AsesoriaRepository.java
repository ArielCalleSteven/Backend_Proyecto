package com.devnexus.backend.repository;

import com.devnexus.backend.entity.Asesoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AsesoriaRepository extends JpaRepository<Asesoria, Long> {
    
    List<Asesoria> findByProgramadorId(Long programadorId);

    List<Asesoria> findByEstudianteId(Long estudianteId);

    List<Asesoria> findByEstudianteEmail(String email);
}