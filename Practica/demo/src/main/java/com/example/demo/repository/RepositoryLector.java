package com.example.demo.repository;

import com.example.demo.model.Lector;
import com.example.demo.model.prestamo.Estados;
import com.example.demo.model.prestamo.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryLector extends JpaRepository<Lector,Long> {



}
