package com.example.A5.service;

import com.example.A5.dto.CrearJugadorDTO;
import com.example.A5.dto.RecargarSaldoDTO;
import com.example.A5.exception.ResourceNotFoundException;
import com.example.A5.model.Jugador;
import com.example.A5.repository.JugadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    public JugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public List<Jugador> listarJugadores() {
        return jugadorRepository.findAll();
    }

    public Jugador obtenerPorId(Long id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado con ID: " + id));
    }

    public Jugador crearJugador(CrearJugadorDTO dto) {
        Jugador jugador = Jugador.builder()
                .nombre(dto.getNombre())
                .saldo(dto.getSaldo())
                .activo(true)
                .build();
        return jugadorRepository.save(jugador);
    }

    @Transactional
    public Jugador recargarSaldo(Long jugadorId, RecargarSaldoDTO dto) {
        Jugador jugador = obtenerPorId(jugadorId);
        jugador.setSaldo(jugador.getSaldo() + dto.getMonto());
        return jugadorRepository.save(jugador);
    }
}
