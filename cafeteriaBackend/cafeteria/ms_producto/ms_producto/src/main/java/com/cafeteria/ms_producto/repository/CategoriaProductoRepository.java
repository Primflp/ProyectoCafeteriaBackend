package com.cafeteria.ms_producto.repository;

import com.cafeteria.ms_producto.model.CategoriaProducto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Long> {

}
