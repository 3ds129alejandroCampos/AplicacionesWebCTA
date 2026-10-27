package com.example.demo.service;


import com.example.demo.model.Lector;
import com.example.demo.repository.RepositoryLector;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceLector {

    private final RepositoryLector repositoryLector;

    public ServiceLector(RepositoryLector repositoryLector) {
        this.repositoryLector = repositoryLector;
    }

    @Transactional
    public Lector habilitarLector(Long id){
        Lector lector = repositoryLector.findById(id).orElseThrow(()-> new RuntimeException("Lector no encontrado"));

        lector.setActivo(true);
        return repositoryLector.save(lector);
    }

}
