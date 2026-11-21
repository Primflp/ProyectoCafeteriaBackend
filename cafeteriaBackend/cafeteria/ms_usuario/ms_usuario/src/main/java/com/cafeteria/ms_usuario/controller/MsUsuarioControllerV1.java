package com.cafeteria.ms_usuario.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cafeteria.ms_usuario.dto.UsuarioV1DTO;
import com.cafeteria.ms_usuario.model.Usuario;
import com.cafeteria.ms_usuario.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuario")
public class MsUsuarioControllerV1 {

    @Autowired
    private UsuarioService usuarioService;

    // V1: Solo lectura (GET)
    @GetMapping("/buscartodos")
    public ResponseEntity<List<Usuario>> usuarioFindALL() {
        List<Usuario> listaUsuario = usuarioService.usuarioBuscarTodo();
        if (listaUsuario.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaUsuario);
        }
    }

    // V1: Solo creación (POST)
    @PostMapping("/guardar")
    public ResponseEntity<Usuario> usuarioSave(@RequestBody UsuarioV1DTO dto) {
        // Mapeamos el DTO liviano a la entidad de Base de Datos
        Usuario usuario = new Usuario();
        usuario.setRunUsuario(dto.getRunUsuario());
        usuario.setNombresUsuario(dto.getNombresUsuario());
        usuario.setApellidosUsuario(dto.getApellidosUsuario());
        usuario.setCorreoUsuario(dto.getCorreoUsuario());
        usuario.setContrasenaUsuario(dto.getContrasenaUsuario());

        Usuario usuarioNew = usuarioService.usuarioGuardar(usuario);
        return ResponseEntity.status(201).body(usuarioNew);
    }
}