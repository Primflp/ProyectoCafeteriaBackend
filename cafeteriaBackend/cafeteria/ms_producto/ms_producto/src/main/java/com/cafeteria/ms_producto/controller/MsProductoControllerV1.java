package com.cafeteria.ms_producto.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cafeteria.ms_producto.dto.ProductoV1DTO;
import com.cafeteria.ms_producto.model.Producto;
import com.cafeteria.ms_producto.service.ProductoService;

@RestController
@RequestMapping("/api/v1")
public class MsProductoControllerV1 {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/producto/buscartodos")
    public ResponseEntity<List<ProductoV1DTO>> productoFindAllV1() {
        List<Producto> lista = productoService.productoBuscarTodo();

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ProductoV1DTO> listaV1 = lista.stream().map(p -> {
            ProductoV1DTO dto = new ProductoV1DTO();
            dto.setIdProducto(p.getIdProducto());
            dto.setNombreProducto(p.getNombreProducto());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listaV1);
    }

    @PostMapping("/producto/guardar")
    public ResponseEntity<ProductoV1DTO> productoSaveV1(@RequestBody Producto productoRequest) {
        productoRequest.setPrecioProducto(0.0f);
        productoRequest.setStockProducto(0);
        productoRequest.setDescripcionProducto("Sin descripción (V1)");

        Producto productoNuevo = productoService.productoGuardar(productoRequest);

        ProductoV1DTO dto = new ProductoV1DTO();
        dto.setIdProducto(productoNuevo.getIdProducto());
        dto.setNombreProducto(productoNuevo.getNombreProducto());

        return ResponseEntity.status(201).body(dto);
    }
}