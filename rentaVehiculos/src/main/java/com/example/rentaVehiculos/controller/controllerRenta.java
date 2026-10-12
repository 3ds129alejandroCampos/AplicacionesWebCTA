package com.example.rentaVehiculos.controller;

import com.example.rentaVehiculos.DTO.renta.RentaRequest;
import com.example.rentaVehiculos.DTO.renta.ResponseRentaDTO;
import com.example.rentaVehiculos.service.RentaVehiculosService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/rentaVehiculos")
public class controllerRenta {

    private final RentaVehiculosService rentaVehiculosService;

    public controllerRenta(RentaVehiculosService rentaVehiculosService) {
        this.rentaVehiculosService = rentaVehiculosService;
    }

    @PostMapping
    public ResponseEntity<ResponseRentaDTO> peticionDeRenta(@RequestBody @Valid RentaRequest payload){
        return ResponseEntity.status(200).body(rentaVehiculosService.calcularRenta(payload));

    }
}
