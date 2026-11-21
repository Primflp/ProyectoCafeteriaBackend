package com.cafeteria.ms_usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria.ms_usuario.model.Usuario;
import com.cafeteria.ms_usuario.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Read All

    public List<Usuario> usuarioBuscarTodo (){
        return usuarioRepository.findAll();
    }

    // 1. Create

    public Usuario usuarioGuardar (Usuario usuario){
        usuario.setFechaCreacionUsuario(LocalDateTime.now());
        usuario.setFechaActualizacionUsuario(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

    // 2. Read

    public Usuario usuarioBuscarPorId(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    // 3. Update

    public Usuario usuarioActualizar(Long id, Usuario actualizarUsuario){

        if (usuarioRepository.existsById(id)) {

            Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);

            usuarioExistente.setRunUsuario(actualizarUsuario.getRunUsuario());
            usuarioExistente.setNombresUsuario(actualizarUsuario.getNombresUsuario());
            usuarioExistente.setApellidosUsuario(actualizarUsuario.getApellidosUsuario());
            usuarioExistente.setCorreoUsuario(actualizarUsuario.getCorreoUsuario());
            usuarioExistente.setFechaNacimientoUsuario(actualizarUsuario.getFechaNacimientoUsuario());
            usuarioExistente.setDireccionUsuario(actualizarUsuario.getDireccionUsuario());
            usuarioExistente.setTelefonoUsuario(actualizarUsuario.getTelefonoUsuario());
            usuarioExistente.setContrasenaUsuario(actualizarUsuario.getContrasenaUsuario());
            usuarioExistente.setRolUsuario(actualizarUsuario.getRolUsuario());
            usuarioExistente.setEstadoCuentaUsuario(actualizarUsuario.getEstadoCuentaUsuario());

            return usuarioRepository.save(usuarioExistente);

        }
        return null;
    }

    // 4. Delete

    public void usuarioEliminar (Long id){
        usuarioRepository.deleteById(id);
    }
}