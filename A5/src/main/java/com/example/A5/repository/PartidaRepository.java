package com.example.A5.repository;

import com.example.A5.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByJugadorId(Long jugadorId);
    List<Partida> findByJugadorIdOrderByFechaDesc(Long jugadorId);
}
