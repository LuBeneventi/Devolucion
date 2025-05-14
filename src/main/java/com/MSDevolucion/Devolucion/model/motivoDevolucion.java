package com.MSDevolucion.Devolucion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class motivoDevolucion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMotivo;

    @Column(length = 30, nullable = false)
    private String descripcion;
}
