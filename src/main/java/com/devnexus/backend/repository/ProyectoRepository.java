package com.devnexus.backend.repository;

import com.devnexus.backend.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    List<Proyecto> findByProgramadorId(Long programadorId);
}