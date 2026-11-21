package com.cafeteria.ms_boleta.repository;

import com.cafeteria.ms_boleta.model.Boleta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BoletaRepository extends JpaRepository<Boleta, Long> {
}
