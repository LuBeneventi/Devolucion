package com.MSDevolucion.Devolucion.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MSDevolucion.Devolucion.model.Devolucion;
import com.MSDevolucion.Devolucion.model.estadoDev;
import com.MSDevolucion.Devolucion.model.motivoDevolucion;
import com.MSDevolucion.Devolucion.repository.devolucionRepository;
import com.MSDevolucion.Devolucion.repository.motivoRepository;

@Service
public class devolucionService {

    @Autowired
    private devolucionRepository dRepo;
    @Autowired
    private motivoRepository motivoRepo;

    public Devolucion crear(Devolucion devolucion) {

        int idMotivo = devolucion.getRazon().getIdMotivo();
        motivoDevolucion motivo = motivoRepo.findById(idMotivo)
                .orElseThrow(() -> new RuntimeException("Motivo no encontrado con ID: " + idMotivo));
        devolucion.setRazon(motivo); 
        devolucion.setEstado(estadoDev.SOLICITADA);
        devolucion.setFechaSolicitud(LocalDate.now());
        return dRepo.save(devolucion);
    }

    public Optional<Devolucion> buscarPorId(int id) {
        return dRepo.findById(id);
    }

    public Devolucion aceptar(int id) {
        Devolucion d = dRepo.findById(id).orElseThrow();
        d.setEstado(estadoDev.ACEPTADA);
        return dRepo.save(d);
    }

    public Devolucion cancelar(int id) {
        Devolucion d = dRepo.findById(id).orElseThrow();
        if (d.getEstado() == estadoDev.SOLICITADA) {
            d.setEstado(estadoDev.CANCELADA);
            return dRepo.save(d);
        }
        throw new IllegalStateException("No se puede cancelar si ya fue aceptada o cancelada.");
    }

    public List<Devolucion> listar() {
        return dRepo.findAll();
    }

    public List<Devolucion> listarPorVenta(int idVenta) {
        return dRepo.findByIdVenta(idVenta);
    }
}
