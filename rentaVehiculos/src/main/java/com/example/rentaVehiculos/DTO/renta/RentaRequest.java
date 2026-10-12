package com.example.rentaVehiculos.DTO.renta;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentaRequest {

    @NotBlank(message = "Nombre requerido")
    private String nombreCliente;

    @Min(value = 18,message = "Edad insuficiente")
    private int edad;

    @NotBlank(message = "El tipo de vehículo no puede estar vacío")
    @Pattern(
            regexp = "^(COMPACTO|SEDAN|SUV|CAMIONETA)$",
            message = "El tipo de vehículo no es válido. Opciones permitidas: COMPACTO, SEDAN, SUV, CAMIONETA"
    )
    private String tipoVehiculo;

    @Min(value = 1,message = "un dia como minimo")
    @Max(value = 30,message = "30 dias maximos de renta")
    private int diasRenta;

    @Min(value = 1,message = "valor invalido")
    private int kilometrosEstimados;

    private boolean seguroCompleto;
}
