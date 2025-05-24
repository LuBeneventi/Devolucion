package com.MSDevolucion.Devolucion.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class productoDevuelto {
    private int idProducto;
    private int cantidad;
}
