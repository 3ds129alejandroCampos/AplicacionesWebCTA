package com.example.demo.controller;


import com.example.demo.model.request.RequestPrestamoNuevo;
import com.example.demo.service.ServicePrestamo;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prestamo")
public class ControllerPrestamo {

    private final ServicePrestamo servicePrestamo;

    public ControllerPrestamo(ServicePrestamo servicePrestamo) {
        this.servicePrestamo = servicePrestamo;
    }


    @PostMapping
    public ResponseEntity<ResponsePrestamoNuevo> crearPrestamo(@Valid @RequestBody RequestPrestamoNuevo  payload){

    }
}
