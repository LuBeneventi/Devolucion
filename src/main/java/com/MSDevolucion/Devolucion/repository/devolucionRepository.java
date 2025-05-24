package com.MSDevolucion.Devolucion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MSDevolucion.Devolucion.model.Devolucion;

@Repository
public interface devolucionRepository extends JpaRepository<Devolucion, Integer>{

    List<Devolucion> findByIdVenta(int idVenta);
    
}
