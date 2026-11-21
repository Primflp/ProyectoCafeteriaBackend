package com.cafeteria.ms_producto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria.ms_producto.dto.StockProductoDTO;
import com.cafeteria.ms_producto.model.Producto;
import com.cafeteria.ms_producto.repository.ProductoRepository;

import java.util.List;

@Service

public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Read All

    public List<Producto> productoBuscarTodo (){
        return productoRepository.findAll();
    }

    // 1. Create

    public Producto productoGuardar (Producto producto){
        return productoRepository.save(producto);
    }

    // 2. Read

    public Producto productoBuscarPorId(Long id){
        return productoRepository.findById(id).orElse(null);
    }

    // 3. Update

    public Producto productoActualizar(Long id, Producto actualizarProducto){

        if (productoRepository.existsById(id)) {

            Producto productoExistente = productoRepository.findById(id).orElse(null);

            productoExistente.setNombreProducto(actualizarProducto.getNombreProducto());
            productoExistente.setPrecioProducto(actualizarProducto.getPrecioProducto());
            productoExistente.setDescripcionProducto(actualizarProducto.getDescripcionProducto());
            productoExistente.setStockProducto(actualizarProducto.getStockProducto());

            return productoRepository.save(productoExistente);

        }
        return null;
    }

    // 4. Delete

    public void productoEliminar (Long id){
        productoRepository.deleteById(id);
    }

    // Metodo Custom 1

    public List<StockProductoDTO> productoStock (){
        return productoRepository.verificarStockProductos();
    }
}
