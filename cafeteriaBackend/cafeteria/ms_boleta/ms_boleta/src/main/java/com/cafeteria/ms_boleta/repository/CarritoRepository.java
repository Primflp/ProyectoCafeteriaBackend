package com.cafeteria.ms_boleta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafeteria.ms_boleta.model.Carrito;

@Repository 
public interface CarritoRepository extends JpaRepository<Carrito, Long>{
    Optional<Carrito> findByIdUsuario(Long idUsuario);
}
