package com.example.tienda.repository;

import com.example.tienda.model.Producto;

import java.util.List;
import java.util.Optional;

public interface Repository {
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    Optional<Producto> findByNombre(String nombre);
    Producto save(Producto producto);
    String deleteProducto(String nombre);

}
