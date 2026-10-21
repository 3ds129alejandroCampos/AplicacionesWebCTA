package com.example.tienda.service;

import com.example.tienda.DTO.RequestProductoDto;
import com.example.tienda.model.Producto;
import com.example.tienda.repository.RepositoryTienda;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTienda {
    private final RepositoryTienda repositoryTienda;

    public ServiceTienda(RepositoryTienda repositoryTienda) {
        this.repositoryTienda = repositoryTienda;
    }

    public Producto anadirProducto(RequestProductoDto payload){
        Producto nuevoPoducto = new Producto(
                null,
                payload.getNombreProducto(),
                payload.getPrecio(),
                payload.getStock()
        );
        return repositoryTienda.save(nuevoPoducto);

    }

    public List<Producto> devolverProductos() {
        return repositoryTienda.findAll();
    }

    public Optional<Producto> busquedaProducto(String nombre) {
        return repositoryTienda.findByNombre(nombre);
    }

    public String eliminarProducto(String nombre) {
        return repositoryTienda.deleteProducto(nombre);
    }
}
