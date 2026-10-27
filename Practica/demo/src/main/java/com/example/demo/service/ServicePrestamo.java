package com.example.demo.service;

import com.example.demo.exceptions.ReglaDeNegocio;
import com.example.demo.model.Lector;
import com.example.demo.model.Libro;
import com.example.demo.model.prestamo.Estados;
import com.example.demo.model.prestamo.Prestamo;
import com.example.demo.model.request.RequestPrestamoNuevo;
import com.example.demo.repository.RepositoryLector;
import com.example.demo.repository.RepositoryLibro;
import com.example.demo.repository.RepositoryPrestamo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServicePrestamo {

    private final RepositoryLector repositoryLector;
    private final RepositoryLibro repositoryLibro;
    private final RepositoryPrestamo repositoryPrestamo;

    public ServicePrestamo(RepositoryLector repositoryLector, RepositoryLibro repositoryLibro, RepositoryPrestamo repositoryPrestamo) {
        this.repositoryLector = repositoryLector;
        this.repositoryLibro = repositoryLibro;
        this.repositoryPrestamo = repositoryPrestamo;
    }

    @Transactional
    public Prestamo crearNuevoPrestamo(RequestPrestamoNuevo payload){

        Lector lector = repositoryLector.findById(payload.getIdLector()).orElseThrow(() -> new RuntimeException("id sin coincidencia") );
        Libro libro= repositoryLibro.findById(payload.getIdLibro()).orElseThrow(() -> new RuntimeException("id sin coincidencia") );

        if(!lector.isActivo()){
            throw new ReglaDeNegocio("El estado del lector es inactivo");
        }

        if(libro.getEjemplaresDisponibles()<0){
            throw new ReglaDeNegocio("Libro sin stock disponible");
        }

        int prestamosPermitidos = lector.isMembresiaPremium()? 5 : 3;

        List<Estados> estadosActivos = List.of(Estados.PRESTADO, Estados.VENCIDO);
        int prestamosActivos = repositoryPrestamo.countByLectorIdAndEstadoIn(payload.getIdLector(), estadosActivos);

        if(prestamosActivos>=prestamosPermitidos){
            throw new ReglaDeNegocio("Lector con maximo de prestamos");
        }

        Libro libro1 = repositoryLibro.findById(payload.getIdLibro()).orElseThrow(()->new ReglaDeNegocio("Libro inexistente"));

        if(libro.getEjemplaresDisponibles()<=0){
            throw new ReglaDeNegocio("Libro sin ejemplares disponibles");
        }

        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles()-1);
        repositoryLibro.save(libro);

        Prestamo prestamo = new Prestamo();

        prestamo.setLector(lector);
        prestamo.setLibro(libro);
        prestamo.setEstado(Estados.PRESTADO);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucionMaxima(LocalDate.now().plusDays(14));

        return repositoryPrestamo.save(prestamo);

    }

    public List<Prestamo> obtenerPrestamos() {
        List<Estados> estados = List.of(Estados.PRESTADO,Estados.VENCIDO);
        return repositoryPrestamo.findByEstadoIn(estados);
    }


    @Transactional
    public Prestamo renovarPrestamo(Long id) {

        Prestamo prestamo = repositoryPrestamo.findById(id).orElseThrow(()-> new RuntimeException("Id de prestamo invalido"));



       if(prestamo.getEstado()==Estados.DEVUELTO||prestamo.getEstado()==Estados.VENCIDO){
           throw new ReglaDeNegocio("Prestamo en estado vencido o devuelto");
       }

       LocalDate fecchaNueva = prestamo.getFechaDevolucionMaxima().plusDays(7);

        prestamo.setFechaDevolucionMaxima(fecchaNueva);

        return repositoryPrestamo.save(prestamo);
    }

    public Prestamo devolverLibro(Long id) {
        Prestamo prestamo = repositoryPrestamo.findById(id).orElseThrow(()-> new RuntimeException("Id de prestamo invalido"));

        if(prestamo.getEstado()==Estados.DEVUELTO){
            throw new RuntimeException("Libro ya devuelto");
        }

        prestamo.setEstado(Estados.DEVUELTO);

        Libro libro = prestamo.getLibro();

        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles()+1);

        repositoryLibro.save(libro);

        if(LocalDate.now().isAfter(prestamo.getFechaDevolucionMaxima()) ){
            Lector lector = prestamo.getLector();
            lector.setActivo(false);
            repositoryLector.save(lector);
        }
        return repositoryPrestamo.save(prestamo);
    }
}
