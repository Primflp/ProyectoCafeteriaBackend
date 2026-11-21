package com.cafeteria.ms_boleta.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Entity

@Table(name = "boleta")

@JsonPropertyOrder({
        "idBoleta",
        "fechaBoleta",
        "totalBoleta",
        "detalleBoleta",
        "IdUsuario"
})

public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBoleta;

    private LocalDateTime fechaBoleta;
    private float totalBoleta;

    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("boleta")
    private List<DetalleBoleta> detalleBoleta;

}