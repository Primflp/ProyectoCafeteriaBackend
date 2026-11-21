package com.cafeteria.ms_boleta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cafeteria.ms_boleta.dtoProducto.RequestAgregarProductoCarritoDTO;
import com.cafeteria.ms_boleta.model.Boleta;
import com.cafeteria.ms_boleta.model.Carrito;
import com.cafeteria.ms_boleta.model.DetalleBoleta;

import com.cafeteria.ms_boleta.service.BoletaService;
import com.cafeteria.ms_boleta.service.CarritoService;
import com.cafeteria.ms_boleta.service.DetalleBoletaService;

@RestController
@RequestMapping("/api/v2/msboleta")
public class MsBoletaControllerV2 {

    @Autowired
    private BoletaService boletaService;

    @Autowired
    private DetalleBoletaService detalleBoletaService;

    @Autowired 
    private CarritoService carritoService;

    // Boleta ---

    @GetMapping("/boleta/buscartodas")
    public ResponseEntity<List<Boleta>> boletaFindALL (){

        List<Boleta> listaBoleta = boletaService.boletaBuscarTodo();

        if(listaBoleta.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(listaBoleta);
        }

    }// Read All

    @PostMapping("/boleta/guardar")
    public ResponseEntity<Boleta> boletaSave (@RequestBody Boleta boleta){

        Boleta boletaNew = boletaService.boletaGuardar(boleta);
        return ResponseEntity.ofNullable(boletaNew);

    }// Create

    @GetMapping("/boleta/buscarporid/{id}")
    public ResponseEntity<Boleta> boletaFindById (@PathVariable Long id){

        Boleta boletaNew = boletaService.boletaBuscarPorId(id);
        return ResponseEntity.ofNullable(boletaNew);

    }// Read

    @PutMapping("/boleta/actualizar/{id}")
    public ResponseEntity<Boleta> boletaUpdate (@PathVariable Long id, @RequestBody Boleta boleta){

        Boleta boletaNew = boletaService.boletaBuscarPorId(id);

        if(boletaNew != null){
            boletaService.boletaActualizar(id, boleta);
            return ResponseEntity.ok(boletaNew);
        }else{
            return ResponseEntity.notFound().build();
        }

    }// Update

    @DeleteMapping("/boleta/eliminarporid/{id}")
    public ResponseEntity<Void> boletaDelete (@PathVariable Long id){

        Boleta boletaNew = boletaService.boletaBuscarPorId(id);

        if(boletaNew != null){
            boletaService.boletaEliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }// Delete

    //-----------------------------------------------------------------------

    // Detalle Boleta ---

    @GetMapping("/detalleboleta/buscartodos")
    public ResponseEntity<List<DetalleBoleta>> detalleBoletaFindAll (){

        List<DetalleBoleta> listaDetalles = detalleBoletaService.detalleBoletaBuscarTodo();

        if(listaDetalles.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(listaDetalles);
        }

    }// Read all

    @PostMapping("/detalleboleta/guardar")
    public ResponseEntity<DetalleBoleta> detalleBoletaSave (@RequestBody DetalleBoleta detalleBoleta){

        DetalleBoleta detalleNew = detalleBoletaService.detalleBoletaGuardar(detalleBoleta);
        return ResponseEntity.ofNullable(detalleNew);

    }// Create

    @GetMapping("/detalleboleta/buscarporid/{id}")
    public ResponseEntity<DetalleBoleta> detalleBoletaFindById (@PathVariable Long id){

        DetalleBoleta detalleNew = detalleBoletaService.detalleBoletaBuscarPorId(id);
        return ResponseEntity.ofNullable(detalleNew);

    }// Read

    @PutMapping("/detalleboleta/actualizar/{id}")
    public ResponseEntity<DetalleBoleta> detalleBoletaUpdate (@PathVariable Long id, @RequestBody DetalleBoleta detalleBoleta){

        DetalleBoleta detalleNew = detalleBoletaService.detalleBoletaBuscarPorId(id);

        if(detalleNew != null){
            detalleBoletaService.detalleBoletaActualizar(id, detalleBoleta);
            return ResponseEntity.ok(detalleNew);
        }else{
            return ResponseEntity.notFound().build();
        }

    }// Update

    @DeleteMapping("/detalleboleta/eliminarporid/{id}")
    public ResponseEntity<Void> detalleBoletaDelete (@PathVariable Long id){

        DetalleBoleta detalleNew = detalleBoletaService.detalleBoletaBuscarPorId(id);

        if(detalleNew != null){
            detalleBoletaService.detalleBoletaEliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }// Delete

    //-----------------------------------------------------------------------

    @PutMapping("/detalle/{idDetalle}/vincular/{idBoleta}")
    public ResponseEntity<?> linkDetalleToBoleta (@PathVariable Long idDetalle, @PathVariable Long idBoleta) {

        DetalleBoleta detalleBoleta = detalleBoletaService.detalleBoletaBuscarPorId(idDetalle);

        Boleta boleta = boletaService.boletaBuscarPorId(idBoleta);

        if (detalleBoleta != null) {
            if (boleta != null) {
                detalleBoletaService.vincularDetalleABoleta(idDetalle, idBoleta);
                return ResponseEntity.ok(boleta);
            }else{
                return ResponseEntity.notFound().build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }

    }// 1

    @PutMapping("/detalle/{idDetalle}/desvincular")
    public ResponseEntity<?> unlinkDetalleFromBoleta (@PathVariable Long idDetalle){

        DetalleBoleta detalleBoleta = detalleBoletaService.detalleBoletaBuscarPorId(idDetalle);

        if(detalleBoleta != null) {
            detalleBoletaService.desvincularDetalleDeBoleta(idDetalle);
            return ResponseEntity.ok(detalleBoleta);
        }else{
            return ResponseEntity.notFound().build();
        }

    }// 2

    // Carrito

    @GetMapping("/carrito/obtenerCarrito/{idUsuario}")
    public ResponseEntity<Carrito> obtenerOCrearCarritoController(@PathVariable Long idUsuario) {
        Carrito carrito = carritoService.obtenerOCrearCarrito(idUsuario);
        return ResponseEntity.ok(carrito);
    }

    @PostMapping("/carrito/agregarProducto")
    public ResponseEntity<Carrito> agregarProducto(@RequestBody RequestAgregarProductoCarritoDTO request) {
        Carrito carritoActualizado = carritoService.agregarProducto(
                request.getIdUsuario(),
                request.getIdProducto(),
                request.getCantidad()
        );
        return ResponseEntity.ok(carritoActualizado);
}

}
