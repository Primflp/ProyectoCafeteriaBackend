package com.cafeteria.ms_boleta.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cafeteria.ms_boleta.dto.BoletaV1DTO;
import com.cafeteria.ms_boleta.model.Boleta;
import com.cafeteria.ms_boleta.model.DetalleBoleta;
import com.cafeteria.ms_boleta.service.BoletaService;
import com.cafeteria.ms_boleta.service.DetalleBoletaService;

@RestController
@RequestMapping("/api/v1/msboleta")
public class MsBoletaControllerV1 {

    @Autowired
    private BoletaService boletaService;

    @Autowired
    private DetalleBoletaService detalleBoletaService;

    // --- Boleta (Solo GET y POST) ---
    @GetMapping("/boleta/buscartodas")
    public ResponseEntity<List<Boleta>> boletaFindALL() {
        List<Boleta> listaBoleta = boletaService.boletaBuscarTodo();
        if (listaBoleta.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaBoleta);
        }
    }

    // --- Detalle Boleta (Solo GET y POST) ---
    @GetMapping("/detalleboleta/buscartodos")
    public ResponseEntity<List<DetalleBoleta>> detalleBoletaFindAll() {
        List<DetalleBoleta> listaDetalles = detalleBoletaService.detalleBoletaBuscarTodo();
        if (listaDetalles.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaDetalles);
        }
    }

    @PostMapping("/boleta/guardar")
    public ResponseEntity<Boleta> boletaSave(@RequestBody BoletaV1DTO dto) {
        Boleta boleta = new Boleta();
        boleta.setFechaBoleta(dto.getFechaBoleta());
        boleta.setTotalBoleta(dto.getTotalBoleta());

        Boleta boletaNew = boletaService.boletaGuardar(boleta);
        return ResponseEntity.status(201).body(boletaNew);
    }
}