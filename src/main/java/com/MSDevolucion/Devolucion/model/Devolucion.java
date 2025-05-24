package com.MSDevolucion.Devolucion.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Devolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDev;

    @Column(nullable = false)
    private int idVenta;

    @ElementCollection
    private List<productoDevuelto> productos;

    @ManyToOne
    @JoinColumn(name = "idMotivo")
    private motivoDevolucion razon;

    @Column(nullable = true, length = 250)
    private String comentario;

    @Enumerated(EnumType.STRING)
    private estadoDev estado;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaSolicitud;
}
