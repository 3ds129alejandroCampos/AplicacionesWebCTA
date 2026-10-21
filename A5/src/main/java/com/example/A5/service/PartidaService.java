package com.example.A5.service;

import com.example.A5.dto.IniciarPartidaDTO;
import com.example.A5.exception.JugadorInactivoException;
import com.example.A5.exception.PartidaFinalizadaException;
import com.example.A5.exception.ResourceNotFoundException;
import com.example.A5.exception.SaldoInsuficienteException;
import com.example.A5.model.EstadoPartida;
import com.example.A5.model.Jugador;
import com.example.A5.model.Partida;
import com.example.A5.model.Tiro;
import com.example.A5.repository.JugadorRepository;
import com.example.A5.repository.PartidaRepository;
import com.example.A5.repository.TiroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class PartidaService {

    private final PartidaRepository partidaRepository;
    private final JugadorRepository jugadorRepository;
    private final TiroRepository tiroRepository;
    private final Random random = new Random();

    public PartidaService(PartidaRepository partidaRepository, JugadorRepository jugadorRepository, TiroRepository tiroRepository) {
        this.partidaRepository = partidaRepository;
        this.jugadorRepository = jugadorRepository;
        this.tiroRepository = tiroRepository;
    }

    @Transactional
    public Partida iniciarPartida(IniciarPartidaDTO dto) {
        Jugador jugador = jugadorRepository.findById(dto.getJugadorId())
                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado con ID: " + dto.getJugadorId()));

        if (Boolean.FALSE.equals(jugador.getActivo())) {
            throw new JugadorInactivoException("El jugador no está activo para iniciar una partida");
        }

        if (jugador.getSaldo() < dto.getApuesta()) {
            throw new SaldoInsuficienteException("Saldo insuficiente. Saldo actual: " + jugador.getSaldo() + ", apuesta requerida: " + dto.getApuesta());
        }

        jugador.setSaldo(jugador.getSaldo() - dto.getApuesta());
        jugadorRepository.save(jugador);

        Partida partida = Partida.builder()
                .fecha(LocalDateTime.now())
                .estado(EstadoPartida.EN_JUEGO)
                .jugador(jugador)
                .apuesta(dto.getApuesta())
                .build();

        return partidaRepository.save(partida);
    }

    @Transactional
    public Tiro realizarTiro(Long partidaId) {
        boolean esGanador = false;
        Partida partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new ResourceNotFoundException("Partida no encontrada con ID: " + partidaId));

        if (partida.getEstado() != EstadoPartida.EN_JUEGO) {
            throw new PartidaFinalizadaException("No se puede realizar un tiro en una partida finalizada");
        }


        int valorDado1 = random.nextInt(6) + 1;
        int valorDado2 = random.nextInt(6) + 1;
        int suma = valorDado1 + valorDado2;

        if (suma == 7 || suma == 11) {

            esGanador = true;
            partida.setEstado(EstadoPartida.FINALIZADA);
            Jugador jugador = partida.getJugador();
            jugador.setSaldo(jugador.getSaldo() + (partida.getApuesta() * 2));
            jugadorRepository.save(jugador);
            partidaRepository.save(partida);
        } else if (suma == 2 || suma == 3 || suma == 12) {

            esGanador = false;
            partida.setEstado(EstadoPartida.FINALIZADA);
            partidaRepository.save(partida);
        } else {

            esGanador = false;
        }

        Tiro tiro = Tiro.builder()
                .partida(partida)
                .valorDado1(valorDado1)
                .valorDado2(valorDado2)
                .suma(suma)
                .esGanador(esGanador)
                .build();

        return tiroRepository.save(tiro);
    }

    @Transactional
    public Partida finalizarPartidaManual(Long partidaId) {
        Partida partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new ResourceNotFoundException("Partida no encontrada con ID: " + partidaId));

        if (partida.getEstado() == EstadoPartida.FINALIZADA) {
            throw new PartidaFinalizadaException("La partida ya se encuentra finalizada");
        }

        partida.setEstado(EstadoPartida.FINALIZADA);
        return partidaRepository.save(partida);
    }

    public List<Partida> obtenerHistorialPartida(Long jugadorId) {
        if (!jugadorRepository.existsById(jugadorId)) {
            throw new ResourceNotFoundException("Jugador no encontrado con ID: " + jugadorId);
        }
        return partidaRepository.findByJugadorIdOrderByFechaDesc(jugadorId);
    }

    public Partida obtenerPartida(Long partidaId) {
        return partidaRepository.findById(partidaId)
                .orElseThrow(() -> new ResourceNotFoundException("Partida no encontrada con ID: " + partidaId));
    }

    public List<Tiro> obtenerTirosDePartida(Long partidaId) {
        if (!partidaRepository.existsById(partidaId)) {
            throw new ResourceNotFoundException("Partida no encontrada con ID: " + partidaId);
        }
        return tiroRepository.findByPartidaId(partidaId);
    }
}
