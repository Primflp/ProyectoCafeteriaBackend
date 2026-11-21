package com.cafeteria.ms_boleta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria.ms_boleta.model.Boleta;
import com.cafeteria.ms_boleta.repository.BoletaRepository;

import java.util.List;

@Service

public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    // Read All

    public List<Boleta> boletaBuscarTodo (){
        return boletaRepository.findAll();
    }

    // 1. Create

    public Boleta boletaGuardar (Boleta boleta){
        return boletaRepository.save(boleta);
    }

    // 2. Read

    public Boleta boletaBuscarPorId(Long id){
        return boletaRepository.findById(id).orElse(null);
    }

    // 3. Update

    public Boleta boletaActualizar(Long id, Boleta actualizarBoleta){

        if (boletaRepository.existsById(id)) {

            Boleta boletaExistente = boletaRepository.findById(id).orElse(null);

            boletaExistente.setFechaBoleta(actualizarBoleta.getFechaBoleta());
            boletaExistente.setTotalBoleta(actualizarBoleta.getTotalBoleta());

            return boletaRepository.save(boletaExistente);

        }
        return null;
    }

    // 4. Delete

    public void boletaEliminar (Long id){
        boletaRepository.deleteById(id);
    }

}