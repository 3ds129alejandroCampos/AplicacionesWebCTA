package com.example.A5;

import com.example.A5.dto.CrearJugadorDTO;
import com.example.A5.dto.IniciarPartidaDTO;
import com.example.A5.dto.RecargarSaldoDTO;
import com.example.A5.exception.JugadorInactivoException;
import com.example.A5.exception.PartidaFinalizadaException;
import com.example.A5.exception.ResourceNotFoundException;
import com.example.A5.exception.SaldoInsuficienteException;
import com.example.A5.model.EstadoPartida;
import com.example.A5.model.Jugador;
import com.example.A5.model.Partida;
import com.example.A5.model.Tiro;
import com.example.A5.service.JugadorService;
import com.example.A5.service.PartidaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JuegoServiceTest {

    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private PartidaService partidaService;

    private Jugador jugadorActivo;
    private Jugador jugadorInactivo;

    @BeforeEach
    void setUp() {
        jugadorActivo = jugadorService.crearJugador(
                CrearJugadorDTO.builder()
                        .nombre("Carlos")
                        .saldo(100.0)
                        .activo(true)
                        .build()
        );

        jugadorInactivo = jugadorService.crearJugador(
                CrearJugadorDTO.builder()
                        .nombre("Pedro")
                        .saldo(50.0)
                        .activo(false)
                        .build()
        );
    }

    @Test
    @DisplayName("iniciarPartida - éxito descuenta saldo y crea partida EN_JUEGO")
    void testIniciarPartidaExito() {
        IniciarPartidaDTO dto = IniciarPartidaDTO.builder()
                .jugadorId(jugadorActivo.getId())
                .apuesta(30.0)
                .build();

        Partida partida = partidaService.iniciarPartida(dto);

        assertNotNull(partida.getId());
        assertEquals(EstadoPartida.EN_JUEGO, partida.getEstado());
        assertEquals(30.0, partida.getApuesta());

        Jugador jugadorActualizado = jugadorService.obtenerPorId(jugadorActivo.getId());
        assertEquals(70.0, jugadorActualizado.getSaldo());
    }

    @Test
    @DisplayName("iniciarPartida - error si jugador inactivo")
    void testIniciarPartidaJugadorInactivo() {
        IniciarPartidaDTO dto = IniciarPartidaDTO.builder()
                .jugadorId(jugadorInactivo.getId())
                .apuesta(10.0)
                .build();

        assertThrows(JugadorInactivoException.class, () -> partidaService.iniciarPartida(dto));
    }

    @Test
    @DisplayName("iniciarPartida - error si saldo insuficiente")
    void testIniciarPartidaSaldoInsuficiente() {
        IniciarPartidaDTO dto = IniciarPartidaDTO.builder()
                .jugadorId(jugadorActivo.getId())
                .apuesta(200.0)
                .build();

        assertThrows(SaldoInsuficienteException.class, () -> partidaService.iniciarPartida(dto));
    }

    @Test
    @DisplayName("iniciarPartida - error si jugador no existe")
    void testIniciarPartidaJugadorNoExiste() {
        IniciarPartidaDTO dto = IniciarPartidaDTO.builder()
                .jugadorId(9999L)
                .apuesta(10.0)
                .build();

        assertThrows(ResourceNotFoundException.class, () -> partidaService.iniciarPartida(dto));
    }

    @Test
    @DisplayName("realizarTiro - genera valores válidos entre 1 y 6 y calcula suma")
    void testRealizarTiro() {
        IniciarPartidaDTO dto = IniciarPartidaDTO.builder()
                .jugadorId(jugadorActivo.getId())
                .apuesta(20.0)
                .build();
        Partida partida = partidaService.iniciarPartida(dto);

        Tiro tiro = partidaService.realizarTiro(partida.getId());

        assertNotNull(tiro.getId());
        assertTrue(tiro.getValorDado1() >= 1 && tiro.getValorDado1() <= 6);
        assertTrue(tiro.getValorDado2() >= 1 && tiro.getValorDado2() <= 6);
        assertEquals(tiro.getValorDado1() + tiro.getValorDado2(), tiro.getSuma());
    }

    @Test
    @DisplayName("finalizarPartidaManual - cambia estado a FINALIZADA")
    void testFinalizarPartidaManual() {
        IniciarPartidaDTO dto = IniciarPartidaDTO.builder()
                .jugadorId(jugadorActivo.getId())
                .apuesta(20.0)
                .build();
        Partida partida = partidaService.iniciarPartida(dto);

        Partida finalizada = partidaService.finalizarPartidaManual(partida.getId());
        assertEquals(EstadoPartida.FINALIZADA, finalizada.getEstado());

        // Intentar tirar en partida finalizada debe fallar
        assertThrows(PartidaFinalizadaException.class, () -> partidaService.realizarTiro(partida.getId()));
    }

    @Test
    @DisplayName("recargarSaldo - suma saldo correctamente")
    void testRecargarSaldo() {
        RecargarSaldoDTO dto = RecargarSaldoDTO.builder()
                .monto(50.0)
                .build();

        Jugador actualizado = jugadorService.recargarSaldo(jugadorActivo.getId(), dto);
        assertEquals(150.0, actualizado.getSaldo());
    }

    @Test
    @DisplayName("obtenerHistorialPartida - lista partidas del jugador")
    void testObtenerHistorialPartida() {
        partidaService.iniciarPartida(IniciarPartidaDTO.builder().jugadorId(jugadorActivo.getId()).apuesta(10.0).build());
        partidaService.iniciarPartida(IniciarPartidaDTO.builder().jugadorId(jugadorActivo.getId()).apuesta(20.0).build());

        List<Partida> historial = partidaService.obtenerHistorialPartida(jugadorActivo.getId());
        assertEquals(2, historial.size());
    }
}
