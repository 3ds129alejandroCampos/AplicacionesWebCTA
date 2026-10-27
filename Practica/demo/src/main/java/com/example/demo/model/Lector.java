package com.example.demo.model;


import com.example.demo.model.prestamo.Prestamo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String nombre;

    @Column(nullable = false)
    boolean activo;

    @Column(nullable = false)
    boolean membresiaPremium;

    @OneToMany(mappedBy = "lector")
    @JsonIgnore
    List<Prestamo> prestamos;

}
