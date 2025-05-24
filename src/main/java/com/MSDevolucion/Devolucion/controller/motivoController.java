package com.MSDevolucion.Devolucion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MSDevolucion.Devolucion.model.motivoDevolucion;
import com.MSDevolucion.Devolucion.service.motivoService;

@RestController
@RequestMapping("/api/motivos")
public class motivoController {
    
    @Autowired
    private motivoService mService;

    @GetMapping
    public List<motivoDevolucion> listarMotivos() {
        return mService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<motivoDevolucion> obtenerPorId(@PathVariable int id) {
        return mService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/generar")
    public ResponseEntity<motivoDevolucion> crearMotivo(@RequestBody motivoDevolucion motivo) {
        if (motivo.getDescripcion() == null || motivo.getDescripcion().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(mService.guardar(motivo));
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<motivoDevolucion> actualizarMotivo(@PathVariable int id, @RequestBody motivoDevolucion motivo) {
        if (motivo.getDescripcion() == null || motivo.getDescripcion().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return mService.actualizar(id, motivo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/borrar")
    public ResponseEntity<Void> eliminarMotivo(@PathVariable int id) {
        return mService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
