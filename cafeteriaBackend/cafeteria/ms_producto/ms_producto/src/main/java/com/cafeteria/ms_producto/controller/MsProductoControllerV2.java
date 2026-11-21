package com.cafeteria.ms_producto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cafeteria.ms_producto.model.Producto;
import com.cafeteria.ms_producto.dto.StockProductoDTO;
import com.cafeteria.ms_producto.model.CategoriaProducto;

import com.cafeteria.ms_producto.service.ProductoService;
import com.cafeteria.ms_producto.service.CategoriaProductoService;

@RestController
@RequestMapping("/api/v2/")

public class MsProductoControllerV2 {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaProductoService categoriaProductoService;

    // Producto ---

    @GetMapping("/producto/buscartodos")
    public ResponseEntity<List<Producto>> productoFindALL (){

        List<Producto> listaProducto = productoService.productoBuscarTodo();

        if(listaProducto.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(listaProducto);
        }

    }// Read All

    @PostMapping("/producto/guardar")
    public ResponseEntity<Producto> productoSave (@RequestBody Producto producto){

        Producto ProductoNew = productoService.productoGuardar(producto);
        return ResponseEntity.ofNullable(ProductoNew);

    }// Create

    @GetMapping("/producto/buscarporid/{id}")
    public ResponseEntity<Producto> productoFindById (@PathVariable Long id){

        Producto productoNew = productoService.productoBuscarPorId(id);
        return ResponseEntity.ofNullable(productoNew);

    }// Read

    @PutMapping("/producto/actualizar/{id}")
    public ResponseEntity<Producto> productoUpdate (@PathVariable Long id, @RequestBody Producto producto){

        Producto productoNew = productoService.productoBuscarPorId(id);

        if(productoNew != null){
            productoService.productoActualizar(id, producto);
            return ResponseEntity.ok(productoNew);
        }else{
            return ResponseEntity.notFound().build();
        }

    }// Update

    @DeleteMapping("/producto/eliminarporid/{id}")
    public ResponseEntity<Void> productoDelete (@PathVariable Long id){

        Producto productoNew = productoService.productoBuscarPorId(id);

        if(productoNew != null){
            productoService.productoEliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }// Delete

    //-----------------------------------------------------------------------

    @GetMapping("/producto/stocktotal")
    public ResponseEntity<List<StockProductoDTO>> productoTotalStock (){

        List<StockProductoDTO> ListaDTO = productoService.productoStock();

        if(ListaDTO.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(ListaDTO);
        }

    }// 1

    // Categoria Producto ---

    @GetMapping("/categoria/buscartodas")
    public ResponseEntity<List<CategoriaProducto>> categoriaProductoFindAll (){

        List<CategoriaProducto> listaCategorias = categoriaProductoService.categoriaProductoBuscarTodo();

        if(listaCategorias.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(listaCategorias);
        }

    }// Read All


    @PostMapping("/categoria/guardar")
    public ResponseEntity<CategoriaProducto> categoriaProductoSave (@RequestBody CategoriaProducto categoriaProducto){

        CategoriaProducto categoriaNew = categoriaProductoService.categoriaProductoGuardar(categoriaProducto);
        return ResponseEntity.ofNullable(categoriaNew);

    }// Create

    @GetMapping("/categoria/buscarporid/{id}")
    public ResponseEntity<CategoriaProducto> categoriaProductoFindById (@PathVariable Long id){

        CategoriaProducto categoriaNew = categoriaProductoService.categoriaProductoBuscarPorId(id);
        return ResponseEntity.ofNullable(categoriaNew);

    }// Read

    @PutMapping("/categoria/actualizar/{id}")
    public ResponseEntity<CategoriaProducto> categoriaProductoUpdate (@PathVariable Long id, @RequestBody CategoriaProducto categoriaProducto){

        CategoriaProducto categoriaNew = categoriaProductoService.categoriaProductoBuscarPorId(id);

        if(categoriaNew != null){
            categoriaProductoService.categoriaProductoActualizar(id, categoriaProducto);
            return ResponseEntity.ok(categoriaNew);
        }else{
            return ResponseEntity.notFound().build();
        }

    }// Update

    @DeleteMapping("/categoria/eliminarporid/{id}")
    public ResponseEntity<Void> categoriaProductoDelete (@PathVariable Long id){

        CategoriaProducto categoriaNew = categoriaProductoService.categoriaProductoBuscarPorId(id);

        if(categoriaNew != null){
            categoriaProductoService.categoriaProductoEliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }// Delete

    //-----------------------------------------------------------------------

    @PutMapping("/categoria/{idCategoria}/vincular/{idProducto}")
    public ResponseEntity<?> linkCategoriaToProducto (@PathVariable Long idCategoria, @PathVariable Long idProducto) {

        CategoriaProducto categoriaProducto = categoriaProductoService.categoriaProductoBuscarPorId(idCategoria);

        if (categoriaProducto == null) {
            return ResponseEntity.notFound().build();
        }

        Producto producto = productoService.productoBuscarPorId(idProducto);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }

        categoriaProductoService.vincularCategoriaAProducto(idCategoria, idProducto);
        
        Producto productoActualizado = productoService.productoBuscarPorId(idProducto);

        return ResponseEntity.ok(productoActualizado);

    }// 1

    @PutMapping("/categoria/{idProducto}/desvincular")
    public ResponseEntity<?> unlinkCategoriaFromProducto (@PathVariable Long idProducto){

        Producto producto = productoService.productoBuscarPorId(idProducto);
        
        if(producto == null) {
            return ResponseEntity.notFound().build();
        }

        if(producto.getCategoriaProducto() == null) {
            return ResponseEntity.badRequest().body("El producto no tiene una categoria");
        }

        categoriaProductoService.desvincularCategoriaDeProducto(idProducto);
        return ResponseEntity.ok(idProducto);

    }// 2

}