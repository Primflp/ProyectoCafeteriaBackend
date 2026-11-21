package com.cafeteria.ms_producto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

@Table(name = "producto")

@JsonPropertyOrder({
        "idProducto",
        "nombreProducto",
        "precioProducto",
        "descripcionProducto",
        "stockProducto",
        "categoriaProducto"
})

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String nombreProducto;
    private float  precioProducto;

    @Column(columnDefinition = "TEXT")
    private String descripcionProducto;
    private Integer stockProducto;

    @ManyToOne
    @JoinColumn(name = "idCategoriaProducto")
    @JsonIgnoreProperties("producto")
    private CategoriaProducto categoriaProducto;
}
