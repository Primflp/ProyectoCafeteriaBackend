package com.cafeteria.ms_boleta.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity

@Table (name = "carrito")

@JsonPropertyOrder({
    "idCarrito",
    "idUsuario",
    "listaDetalleCarritos"
})

public class Carrito {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    private Long idUsuario;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("carrito")
    private List<DetalleCarrito> listaDetalleCarritos = new ArrayList<>();
}
