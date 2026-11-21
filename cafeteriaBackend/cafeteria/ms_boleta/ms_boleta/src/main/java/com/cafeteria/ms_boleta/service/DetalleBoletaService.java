package com.cafeteria.ms_boleta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria.ms_boleta.model.DetalleBoleta;
import com.cafeteria.ms_boleta.model.Boleta;
import com.cafeteria.ms_boleta.repository.DetalleBoletaRepository;
import com.cafeteria.ms_boleta.repository.BoletaRepository;

import java.util.List;

@Service
public class DetalleBoletaService {

    @Autowired
    private DetalleBoletaRepository detalleBoletaRepository;

    @Autowired
    private BoletaRepository boletaRepository;

    // Read All

    public List<DetalleBoleta> detalleBoletaBuscarTodo (){
        return detalleBoletaRepository.findAll();
    }

    // 1. Create

    public DetalleBoleta detalleBoletaGuardar (DetalleBoleta detalleBoleta){
        return detalleBoletaRepository.save(detalleBoleta);
    }

    // 2. Read

    public DetalleBoleta detalleBoletaBuscarPorId(Long id){
        return detalleBoletaRepository.findById(id).orElse(null);
    }

    // 3. Update

    public DetalleBoleta detalleBoletaActualizar(Long id, DetalleBoleta actualizarDetalleBoleta){

        if (detalleBoletaRepository.existsById(id)) {

            DetalleBoleta detalleBoletaExistente = detalleBoletaRepository.findById(id).orElse(null);

            detalleBoletaExistente.setCantidadBoleta(actualizarDetalleBoleta.getCantidadBoleta());
            detalleBoletaExistente.setPrecioUnitario(actualizarDetalleBoleta.getPrecioUnitario());

            return detalleBoletaRepository.save(detalleBoletaExistente);

        }
        return null;
    }

    // 4. Delete

    public void detalleBoletaEliminar (Long id){
        detalleBoletaRepository.deleteById(id);
    }

    // Metodo Custom 1

    public void vincularDetalleABoleta(Long idDetalleBoleta, Long idBoleta) {

        DetalleBoleta detalleBoleta = detalleBoletaRepository.findById(idDetalleBoleta)
                .orElseThrow(() -> new RuntimeException("Detalle de boleta no encontrado"));


        Boleta boleta = boletaRepository.findById(idBoleta)
                .orElseThrow(() -> new RuntimeException("Boleta no encontrada"));

        detalleBoleta.setBoleta(boleta);
        detalleBoletaRepository.save(detalleBoleta);
    }

    // Metodo Custom 2

    public void desvincularDetalleDeBoleta(Long idDetalleBoleta) {

        DetalleBoleta detalleBoleta = detalleBoletaRepository.findById(idDetalleBoleta)
                .orElseThrow(() -> new RuntimeException("Detalle de boleta no encontrado"));

        detalleBoleta.setBoleta(null);
        detalleBoletaRepository.save(detalleBoleta);
    }

}