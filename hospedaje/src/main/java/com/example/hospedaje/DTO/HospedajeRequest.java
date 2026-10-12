package com.example.hospedaje.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospedajeRequest {

    @NotBlank(message = "El nombre del huesped es requerido")
    private String nombreHuesped;

    @NotBlank(message = "El tipo de habitacion no puede estar vacio")
    @Pattern(
            regexp = "^(INDIVIDUAL|DOBLE|SUITE)$",
            message = "Tipo de habitacion no válido. Opciones permitidas: INDIVIDUAL, DOBLE, SUITE"
    )
    private String tipoHabitacion;

    @Min(value = 1, message = "Debe ser al menos 1 noche")
    @Max(value = 30, message = "La reservacion no puede superar los 30 días")
    private int numeroNoches;

    @Min(value = 1, message = "Debe haber al menos 1 huesped")

    private int numeroHuespedes;

    @NotBlank(message = "La temporada no puede estar vacia")
    @Pattern(
            regexp = "^(BAJA|REGULAR|ALTA)$",
            message = "Temporada no válida. Opciones permitidas: BAJA, REGULAR, ALTA"
    )
    private String temporada;

    private boolean incluyeDesayuno;

    private boolean incluyeEstacionamiento;
}
