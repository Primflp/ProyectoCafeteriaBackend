package com.cafeteria.ms_boleta.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cafeteria.ms_boleta.dtoUsuario.UsuarioDTO;

@FeignClient(name = "ms-usuario", url = "localhost:8081/api/v2")
public interface UsuarioClientRest {
    @GetMapping ("/usuario/buscarporid/{id}")
    UsuarioDTO obtenerDatosUsuario(@PathVariable ("id") Long idUsuario);

}
