package com.cafeteria.ms_usuario.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity

@Table(name = "usuario")

@JsonPropertyOrder({
        "idUsuario",
        "runUsuario",
        "nombresUsuario",
        "apellidosUsuario",
        "correoUsuario",
        "contraseña",
        "fechaNacimientoUsuario",
        "direccionUsuario",
        "telefonoUsuario",
        "rolUsuario",
        "estadoCuentaUsuario",
        "fechaCreacionUsuario",
        "fechaActualizacionUsuario",
        "idBoleta"
})

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(unique = true)
    private String runUsuario;

    private String nombresUsuario;
    private String apellidosUsuario;

    @Column(unique = true, nullable = false)
    private String correoUsuario;

    private LocalDate fechaNacimientoUsuario;
    private String direccionUsuario;
    private String telefonoUsuario;

    @Column(nullable = false)
    private String contrasenaUsuario;

    private String rolUsuario;
    private String estadoCuentaUsuario;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacionUsuario;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacionUsuario;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacionUsuario = LocalDateTime.now();
        this.fechaActualizacionUsuario = LocalDateTime.now();
        if (this.estadoCuentaUsuario == null) {
            this.estadoCuentaUsuario = "ACTIVA";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacionUsuario = LocalDateTime.now();
    }

}