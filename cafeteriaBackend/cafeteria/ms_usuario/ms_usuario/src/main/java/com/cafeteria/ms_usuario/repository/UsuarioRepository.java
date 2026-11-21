package com.cafeteria.ms_usuario.repository;

import com.cafeteria.ms_usuario.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreoUsuario(String correoUsuario);
    
}
