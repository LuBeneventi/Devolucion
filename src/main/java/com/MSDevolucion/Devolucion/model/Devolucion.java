package com.MSDevolucion.Devolucion.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "devolucion_id")
    private List<productoDevuelto> productos;

    @Enumerated(EnumType.STRING)
    @Column(name = "motivo", nullable = false)
    private motivoDev motivo;

    @Column(nullable = true, length = 250)
    private String comentario;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private estadoDev estado;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaSolicitud;
}
