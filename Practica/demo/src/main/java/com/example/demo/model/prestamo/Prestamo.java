package com.example.demo.model.prestamo;

import com.example.demo.model.Lector;
import com.example.demo.model.Libro;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaPrestamo;

    @Column(nullable = false)
    private LocalDate fechaDevolucionMaxima;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estados estado;

    @ManyToOne
    @JoinColumn(name = "lector_id")
    @JsonIgnoreProperties("prestamos")
    private Lector lector;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    @JsonIgnoreProperties("prestamosL")
    private Libro libro;
}
