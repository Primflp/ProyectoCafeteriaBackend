package com.cafeteria.ms_producto.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

@Table(name = "categoriaProducto")

@JsonPropertyOrder({
        "idCategoriaProducto",
        "nombreCategoriaProducto"
})

public class CategoriaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCategoriaProducto;

    private String nombreCategoriaProducto;

    @OneToMany(mappedBy = "categoriaProducto")
    @JsonIgnoreProperties("categoriaProducto")
    private List<Producto> listaProductos;


}
