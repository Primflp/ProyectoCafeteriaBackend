package com.cafeteria.ms_boleta.dto;

import lombok.Data;

@Data
public class DetalleBoletaV1DTO {
    private Long idDetalle;
    private int cantidad;
    private Long subtotal;
}