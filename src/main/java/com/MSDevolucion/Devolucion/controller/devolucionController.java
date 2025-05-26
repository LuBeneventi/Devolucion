package com.MSDevolucion.Devolucion.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("api/devoluciones")
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

    @PutMapping("/{id}/terminar")
    public ResponseEntity<Devolucion> terminar(@PathVariable int id) {
        try {
            return ResponseEntity.ok(dService.terminar(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<Devolucion> editarDevolucion(@PathVariable Integer id, @RequestBody Devolucion nuevaDev) {
        return dService.buscarPorId(id)
                .map(devExistente -> {
                    // Campos editables
                    devExistente.setComentario(nuevaDev.getComentario());
                    devExistente.setRazon(nuevaDev.getRazon());
                    devExistente.setProductos(nuevaDev.getProductos());

                    Devolucion actualizada = dService.crear(devExistente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<?> eliminarDevolucion(@PathVariable int id) {
        try {
            dService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
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

    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<List<Devolucion>> listarPorCliente(@PathVariable int idVenta) {

        List<Devolucion> devoluciones = dService.listarPorVenta(idVenta);
        if (devoluciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(dService.listarPorVenta(idVenta));
        }
    }
}
