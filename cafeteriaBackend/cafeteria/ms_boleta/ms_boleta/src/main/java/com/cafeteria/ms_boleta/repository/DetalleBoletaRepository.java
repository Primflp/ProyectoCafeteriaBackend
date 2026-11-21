package com.cafeteria.ms_boleta.repository;

import com.cafeteria.ms_boleta.model.DetalleBoleta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DetalleBoletaRepository extends JpaRepository<DetalleBoleta, Long> {
}
