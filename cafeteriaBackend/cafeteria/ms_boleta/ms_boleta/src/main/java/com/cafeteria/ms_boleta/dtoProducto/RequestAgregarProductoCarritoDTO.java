package com.cafeteria.ms_boleta.dtoProducto;

import lombok.Data;

@Data 

public class RequestAgregarProductoCarritoDTO {
    Long idUsuario;
    Long idProducto;
    Integer cantidad; 
}
