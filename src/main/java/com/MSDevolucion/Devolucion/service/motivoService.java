package com.MSDevolucion.Devolucion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MSDevolucion.Devolucion.model.motivoDevolucion;
import com.MSDevolucion.Devolucion.repository.motivoRepository;

@Service
public class motivoService {
    
    @Autowired
    private motivoRepository mRepo;

    public List<motivoDevolucion> listarTodos() {
        return mRepo.findAll();
    }

    public Optional<motivoDevolucion> obtenerPorId(int id) {
        return mRepo.findById(id);
    }

    public motivoDevolucion guardar(motivoDevolucion motivo) {
        return mRepo.save(motivo);
    }

    public Optional<motivoDevolucion> actualizar(int id, motivoDevolucion motivo) {
        return mRepo.findById(id).map(m -> {
            m.setDescripcion(motivo.getDescripcion());
            return mRepo.save(m);
        });
    }

    public boolean eliminar(int id) {
        if (mRepo.existsById(id)) {
            mRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
