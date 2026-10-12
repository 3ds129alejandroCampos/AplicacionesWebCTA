package com.example.hospedaje.controller;

import com.example.hospedaje.DTO.HospedajeRequest;
import com.example.hospedaje.DTO.ResponseHospedajeDTO;
import com.example.hospedaje.service.HospedajeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/hospedaje")
public class hospedajeController {

    private final HospedajeService hospedajeService;

    public hospedajeController(HospedajeService hospedajeService) {
        this.hospedajeService = hospedajeService;
    }

    @PostMapping
    public ResponseEntity<ResponseHospedajeDTO> cotizarHospedaje(@RequestBody @Valid HospedajeRequest payload) {
        return ResponseEntity.ok(hospedajeService.calcularHospedaje(payload));
    }
}
