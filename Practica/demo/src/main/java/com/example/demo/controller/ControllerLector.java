package com.example.demo.controller;


import com.example.demo.model.Lector;
import com.example.demo.service.ServiceLector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lector")
public class ControllerLector {

    private final ServiceLector serviceLector;

    public ControllerLector(ServiceLector serviceLector) {
        this.serviceLector = serviceLector;
    }


    @PatchMapping("{id}/habilitar")
    public ResponseEntity<Lector> habilitarlector(@PathVariable Long id){
        return ResponseEntity.status(201).body((serviceLector.habilitarLector(id)));
    }


}
