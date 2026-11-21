package com.cafeteria.ms_usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cafeteria.ms_usuario.model.Usuario;
import com.cafeteria.ms_usuario.service.UsuarioService;

@RestController
@RequestMapping("/api/v2")

public class MsUsuarioControllerV2 {

    @Autowired
    private UsuarioService usuarioService;

    // Usuario ---

    @GetMapping("/usuario/buscartodos")
    public ResponseEntity<List<Usuario>> usuarioFindALL (){

        List<Usuario> listaUsuario = usuarioService.usuarioBuscarTodo();

        if(listaUsuario.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(listaUsuario);
        }

    }// Read All

    @PostMapping("/usuario/guardar")
    public ResponseEntity<Usuario> usuarioSave (@RequestBody Usuario usuario){

        Usuario usuarioNew = usuarioService.usuarioGuardar(usuario);
        return ResponseEntity.ofNullable(usuarioNew);

    }// Create

    @GetMapping("/usuario/buscarporid/{id}")
    public ResponseEntity<Usuario> usuarioFindById (@PathVariable Long id){

        Usuario usuarioNew = usuarioService.usuarioBuscarPorId(id);
        return ResponseEntity.ofNullable(usuarioNew);

    }// Read

    @PutMapping("/usuario/actualizar/{id}")
    public ResponseEntity<Usuario> usuarioUpdate (@PathVariable Long id, @RequestBody Usuario usuario){

        Usuario usuarioNew = usuarioService.usuarioBuscarPorId(id);

        if(usuarioNew != null){
            usuarioService.usuarioActualizar(id, usuario);
            return ResponseEntity.ok(usuarioNew);
        }else{
            return ResponseEntity.notFound().build();
        }

    }// Update

    @DeleteMapping("/usuario/eliminarporid/{id}")
    public ResponseEntity<Void> usuarioDelete (@PathVariable Long id){

        Usuario usuarioNew = usuarioService.usuarioBuscarPorId(id);

        if(usuarioNew != null){
            usuarioService.usuarioEliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }// Delete

}

