package com.cafeteria.ms_producto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria.ms_producto.model.CategoriaProducto;
import com.cafeteria.ms_producto.model.Producto;
import com.cafeteria.ms_producto.repository.CategoriaProductoRepository;
import com.cafeteria.ms_producto.repository.ProductoRepository;

import java.util.ArrayList;
import java.util.List;

@Service

public class CategoriaProductoService {

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Read All

    public List<CategoriaProducto> categoriaProductoBuscarTodo (){
        return categoriaProductoRepository.findAll();
    }

    // 1. Create

    public CategoriaProducto categoriaProductoGuardar (CategoriaProducto categoria){
        return categoriaProductoRepository.save(categoria);
    }

    // 2. Read

    public CategoriaProducto categoriaProductoBuscarPorId (Long id){
        return categoriaProductoRepository.findById(id).orElse(null);
    }

    // 3. Update

    public CategoriaProducto categoriaProductoActualizar (Long id, CategoriaProducto actualizarCategoriaProducto){

        if (categoriaProductoRepository.existsById(id)) {

            CategoriaProducto categoriaProductoExistente = categoriaProductoRepository.findById(id).orElse(null);

            categoriaProductoExistente.setNombreCategoriaProducto(actualizarCategoriaProducto.getNombreCategoriaProducto());

            return categoriaProductoRepository.save(categoriaProductoExistente);

        }
        return null;
    }

    // 4. delete

    public void categoriaProductoEliminar (Long id){
        categoriaProductoRepository.deleteById(id);
    }

    // Metodo Custom 1

    public void vincularCategoriaAProducto (Long idCategoriaProducto, Long idProducto) {

        CategoriaProducto categoriaProducto = categoriaProductoRepository.findById(idCategoriaProducto)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));


        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Figura no encontrada"));

        producto.setCategoriaProducto(categoriaProducto);

        List<Producto> lista = categoriaProducto.getListaProductos();
        
        if (lista == null) {
            lista = new ArrayList<>();
        }

        if (!lista.contains(producto)) {
            lista.add(producto);
        }
        
        categoriaProducto.setListaProductos(lista);

        productoRepository.save(producto);
    }

    // Metodo Custom 2

    public void desvincularCategoriaDeProducto(Long idProducto) {

        Producto producto = productoRepository.findById(idProducto)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        CategoriaProducto categoriaActual = producto.getCategoriaProducto();

        if (categoriaActual != null && categoriaActual.getListaProductos() != null) {
            categoriaActual.getListaProductos().remove(producto);
        }

        producto.setCategoriaProducto(null);
        productoRepository.save(producto);
    }

}
