package com.MSDevolucion.Devolucion.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MSDevolucion.Devolucion.model.Devolucion;
import com.MSDevolucion.Devolucion.model.estadoDev;
import com.MSDevolucion.Devolucion.model.productoDevuelto;
import com.MSDevolucion.Devolucion.repository.devolucionRepository;

@Service
public class devolucionService {

    @Autowired
    private devolucionRepository dRepo;


    public Devolucion crear(Devolucion devolucion) {

        if(devolucion.getMotivo() == null){
             throw new IllegalArgumentException("El motivo de devolución no puede ser nulo");
        }
        if(devolucion.getProductos() != null){
            for(productoDevuelto p : devolucion.getProductos()){
                p.setDev(devolucion);
            }
        }
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

    public Devolucion terminar(int id){
        Devolucion d = dRepo.findById(id).orElseThrow();
        if(d.getEstado() == estadoDev.ACEPTADA){
            d.setEstado(estadoDev.TERMINADA);
            return dRepo.save(d);
        }
        throw new IllegalStateException("Solo se pueden terminar solicitudes aceptadas");
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

    public Devolucion editarDevolucion(Devolucion dev){
        return dRepo.save(dev);
    }

    public void eliminar(int id){
        Devolucion d = dRepo.findById(id).orElseThrow();
        if(dRepo.existsById(id) && (d.getEstado() == estadoDev.CANCELADA) || d.getEstado() == estadoDev.TERMINADA){
            dRepo.deleteById(id);
            return;
        }
        throw new NoSuchElementException("Fallo al eliminar");
    }
    public List<Devolucion> listarPorCorreo(String correo){
        return dRepo.findBycorreoCliente(correo);
    }
}
