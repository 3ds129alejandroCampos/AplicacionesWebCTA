package com.example.demo.model;

import com.example.demo.model.prestamo.Prestamo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private int ejemplaresDisponibles;

    @OneToMany(mappedBy = "libro")
    @JsonIgnore
    private List<Prestamo> prestamosL;

}
