package com.example.tienda.repository;

import com.example.tienda.DTO.RequestProductoDto;
import com.example.tienda.model.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@org.springframework.stereotype.Repository
public class RepositoryTienda implements Repository{
    private final HashMap<Long, Producto> productos = new HashMap<>();
    private final AtomicLong contadorId= new AtomicLong(0);

    @Override
    public List<Producto> findAll() {
        return new ArrayList<>(productos.values());
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return Optional.ofNullable(productos.get(id));
    }

    @Override
    public Optional<Producto> findByNombre(String nombre) {
        return productos.values().stream().filter(p -> p.getNombreProducto().equalsIgnoreCase(nombre)).findFirst();
    }

    @Override
    public Producto save(Producto producto) {
        if(!findByNombre(producto.getNombreProducto()).isPresent()) {
            Long id = contadorId.incrementAndGet();
            producto.setIdProducto(id);
            productos.put(id, producto);
            return producto;
        }
        throw new IllegalArgumentException("Nombre repetido");
    }

    @Override
    public String deleteProducto(String nombre) {
       Optional<Producto> encontrado= findByNombre(nombre);
       if(encontrado.isPresent()) {
           Optional<Producto> eliminarProducto = encontrado.map(
                   producto -> {
                       productos.remove(producto.getIdProducto());
                       return producto;
                   });
           return "Producto eliminado";
       }
       return "Producto no encontrado";
    }

}
