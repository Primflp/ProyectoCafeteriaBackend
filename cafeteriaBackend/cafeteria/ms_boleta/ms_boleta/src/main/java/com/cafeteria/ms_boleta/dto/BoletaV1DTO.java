package com.cafeteria.ms_boleta.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoletaV1DTO {
    private Long idBoleta;
    private LocalDateTime fechaBoleta;
    private float totalBoleta;
}