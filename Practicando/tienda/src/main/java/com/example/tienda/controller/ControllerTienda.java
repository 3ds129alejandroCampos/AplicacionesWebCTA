package com.example.tienda.controller;

import com.example.tienda.DTO.RequestProductoDto;
import com.example.tienda.model.Producto;
import com.example.tienda.service.ServiceTienda;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/tienda")
public class ControllerTienda {

    private final ServiceTienda serviceTienda;

    public ControllerTienda(ServiceTienda serviceTienda) {
        this.serviceTienda = serviceTienda;
    }

    @PostMapping("/producto")
    public ResponseEntity<Producto> anadirProducto(@RequestBody @Valid RequestProductoDto payload){
       return ResponseEntity.status(201).body(serviceTienda.anadirProducto(payload));

    }

    @GetMapping("/producto")
    public ResponseEntity<List<Producto>> devolverProductos(){
        return ResponseEntity.status(200).body(serviceTienda.devolverProductos());
    }

    @GetMapping("/productos/{nombre}")
    public ResponseEntity<Optional<Producto>> busquedaProducto(@PathVariable String nombre){
        return ResponseEntity.status(200).body(serviceTienda.busquedaProducto(nombre));
    }

    @DeleteMapping("/productos/{nombre}")
    public String eliminacionProducto(@PathVariable String nombre){
        return serviceTienda.eliminarProducto(nombre);
    }


}
