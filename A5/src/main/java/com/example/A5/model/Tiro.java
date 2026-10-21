package com.example.A5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tiro")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "partida_id", nullable = false)
    @JsonIgnore
    private Partida partida;

    @Column(nullable = false)
    private Integer valorDado1;

    @Column(nullable = false)
    private Integer valorDado2;

    @Column(nullable = false)
    private Integer suma;

    @Column(nullable = false)
    private Boolean esGanador;
}
