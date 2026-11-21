package com.cafeteria.ms_boleta.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data 
@Entity 

@Table (name = "detalleCarrito")

@JsonPropertyOrder ({
    "idDetalleCarrito",
    "idProducto",
    "cantidad",
    "carrito"
})

public class DetalleCarrito {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idDetalleCarrito;
    private Integer cantidad;

    private Long idProducto;

    @ManyToOne
    @JoinColumn (name = "idCarrito")
    @JsonIgnoreProperties("detalleCarrito")
    private Carrito carrito;
}
