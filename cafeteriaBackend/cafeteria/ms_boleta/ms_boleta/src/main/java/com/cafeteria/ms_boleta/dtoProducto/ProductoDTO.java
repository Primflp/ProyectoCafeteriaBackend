package com.cafeteria.ms_boleta.dtoProducto;

import lombok.Data;

@Data 

public class ProductoDTO {
    private Long idProducto;
    private String nombreProducto;
    private float precioProducto;
    private Integer stockProducto;
}
