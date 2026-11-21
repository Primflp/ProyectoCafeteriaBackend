package com.cafeteria.ms_boleta.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

@Table(name = "detalleBoleta")

@JsonPropertyOrder({
        "idDetalleBoleta",
        "cantidadBoleta",
        "precioUnitario",
        "idBoleta",
        "idProducto",
})

public class DetalleBoleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleBoleta;
    
    private Integer cantidadBoleta;
    private float precioUnitario;

    @ManyToOne
    @JoinColumn(name = "idBoleta")
    @JsonIgnoreProperties("detalleBoleta")
    private Boleta boleta;

}
