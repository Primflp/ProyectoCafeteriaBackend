package com.cafeteria.ms_boleta.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cafeteria.ms_boleta.dtoProducto.ProductoDTO;

@FeignClient(name = "ms-producto", url = "${ms.producto.url}")
public interface ProductoClientRest {
    @GetMapping ("/producto/buscarporid/{id}")
    ProductoDTO obtenerDatosProducto(@PathVariable ("id") Long idProducto);

    
}
