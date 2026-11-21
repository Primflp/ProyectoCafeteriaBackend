package com.cafeteria.ms_producto.repository;

import com.cafeteria.ms_producto.dto.StockProductoDTO;
import com.cafeteria.ms_producto.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT f.stockProducto AS stockProducto, f.nombreProducto AS nombreProducto FROM Producto f WHERE f.stockProducto > 0 ")
    List<StockProductoDTO> verificarStockProductos ();
}
