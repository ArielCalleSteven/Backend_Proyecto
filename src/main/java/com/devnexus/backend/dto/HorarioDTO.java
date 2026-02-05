package com.devnexus.backend.dto;

import lombok.Data;

@Data
public class HorarioDTO {
    private Long id;
    private String day;
    private String start;
    private String end;
    private Long programadorId;
}