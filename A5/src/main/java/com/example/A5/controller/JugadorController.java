package com.example.A5.controller;

import com.example.A5.dto.CrearJugadorDTO;
import com.example.A5.dto.RecargarSaldoDTO;
import com.example.A5.model.Jugador;
import com.example.A5.model.Partida;
import com.example.A5.service.JugadorService;
import com.example.A5.service.PartidaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;
    private final PartidaService partidaService;

    public JugadorController(JugadorService jugadorService, PartidaService partidaService) {
        this.jugadorService = jugadorService;
        this.partidaService = partidaService;
    }

    @PostMapping
    public ResponseEntity<Jugador> crearJugador(@Valid @RequestBody CrearJugadorDTO dto) {
        Jugador creado = jugadorService.crearJugador(dto);
        return ResponseEntity.status(201).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Jugador>> listarJugadores() {
        return ResponseEntity.status(200).body(jugadorService.listarJugadores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(jugadorService.obtenerPorId(id));
    }

    @PostMapping("/{id}/recargar")
    public ResponseEntity<Jugador> recargarSaldo(@PathVariable Long id, @Valid @RequestBody RecargarSaldoDTO dto) {
        Jugador actualizado = jugadorService.recargarSaldo(id, dto);
        return ResponseEntity.status(200).body(actualizado);
    }

    @GetMapping("/{id}/partidas")
    public ResponseEntity<List<Partida>> obtenerHistorialPartida(@PathVariable Long id) {
        return ResponseEntity.status(200).body(partidaService.obtenerHistorialPartida(id));
    }
}
