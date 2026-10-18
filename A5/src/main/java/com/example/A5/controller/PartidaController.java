package com.example.A5.controller;

import com.example.A5.dto.IniciarPartidaDTO;
import com.example.A5.model.Partida;
import com.example.A5.model.Tiro;
import com.example.A5.service.PartidaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping("/iniciar")
    public ResponseEntity<Partida> iniciarPartida(@Valid @RequestBody IniciarPartidaDTO dto) {
        Partida partida = partidaService.iniciarPartida(dto);
        return ResponseEntity.status(201).body(partida);
    }

    @PostMapping("/{id}/tiro")
    public ResponseEntity<Tiro> realizarTiro(@PathVariable Long id) {
        Tiro tiro = partidaService.realizarTiro(id);
        return ResponseEntity.status(201).body(tiro);
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Partida> finalizarPartidaManual(@PathVariable Long id) {
        Partida partida = partidaService.finalizarPartidaManual(id);
        return ResponseEntity.status(200).body(partida);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partida> obtenerPartida(@PathVariable Long id) {
        return ResponseEntity.status(200).body(partidaService.obtenerPartida(id));
    }

    @GetMapping("/{id}/tiros")
    public ResponseEntity<List<Tiro>> obtenerTirosDePartida(@PathVariable Long id) {
        return ResponseEntity.status(200).body(partidaService.obtenerTirosDePartida(id));
    }

    @GetMapping("/jugador/{jugadorId}")
    public ResponseEntity<List<Partida>> obtenerHistorialPartida(@PathVariable Long jugadorId) {
        return ResponseEntity.status(200).body(partidaService.obtenerHistorialPartida(jugadorId));
    }
}
