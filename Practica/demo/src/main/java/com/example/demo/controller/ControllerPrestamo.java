package com.example.demo.controller;


import com.example.demo.model.prestamo.Prestamo;
import com.example.demo.model.request.RequestPrestamoNuevo;
import com.example.demo.service.ServicePrestamo;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamo")
public class ControllerPrestamo {

    private final ServicePrestamo servicePrestamo;

    public ControllerPrestamo(ServicePrestamo servicePrestamo) {
        this.servicePrestamo = servicePrestamo;
    }


    @PostMapping
    public ResponseEntity<Prestamo> crearPrestamo(@Valid @RequestBody RequestPrestamoNuevo  payload){
        return ResponseEntity.status(201).body((servicePrestamo.crearNuevoPrestamo(payload)));
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> listaDePrestamos(){
        return ResponseEntity.status(200).body( servicePrestamo.obtenerPrestamos());
    }

    @PatchMapping("/{id}/renovar")
    public ResponseEntity<Prestamo> renovarPrestamo(@PathVariable Long id){
        return ResponseEntity.ok( servicePrestamo.renovarPrestamo(id));
    }

    @PatchMapping("/{id}/devolver")
    public ResponseEntity <Prestamo> devolverLibro(@PathVariable Long id){
        return ResponseEntity.ok( servicePrestamo.devolverLibro(id)  );
    }

    @PatchMapping("/sanciones")
    public ResponseEntity<List<Prestamo>> aplicarSancionesDiarias(){

    }

}
