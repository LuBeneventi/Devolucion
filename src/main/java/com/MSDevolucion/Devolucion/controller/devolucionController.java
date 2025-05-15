package com.MSDevolucion.Devolucion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MSDevolucion.Devolucion.model.Devolucion;
import com.MSDevolucion.Devolucion.service.devolucionService;

@RestController
@RequestMapping("api/Ddevoluciones")
public class devolucionController {

    @Autowired
    private devolucionService dService;

    @PostMapping("/generar")
    public ResponseEntity<Devolucion> crear(@RequestBody Devolucion devolucion) {
        return ResponseEntity.ok(dService.crear(devolucion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> buscarPorId(@PathVariable int id) {
        return dService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Devolucion>> listar() {

        List<Devolucion> devoluciones = dService.listar();
        if (devoluciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(dService.listar());
        }
    }

    @PutMapping("/{id}/aceptar")
    public ResponseEntity<Devolucion> aceptar(@PathVariable int id) {
        try {
            return ResponseEntity.ok(dService.aceptar(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Devolucion> cancelar(@PathVariable int id) {
        try {
            return ResponseEntity.ok(dService.cancelar(id));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Devolucion>> listarPorCliente(@PathVariable int idCliente) {

        List<Devolucion> devoluciones = dService.listarPorCliente(idCliente);
        if (devoluciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(dService.listarPorCliente(idCliente));
        }
    }
}
