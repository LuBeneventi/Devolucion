package com.MSDevolucion.Devolucion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class productoDevuelto {

    @Id
    private int idProducto;
    private int cantidad;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dev_id", nullable = false)
    @JsonIgnore
    private Devolucion dev;
}
