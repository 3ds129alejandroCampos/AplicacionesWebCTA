package com.example.rentaVehiculos.DTO.renta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRentaDTO {
    private double costoRenta;
    private double kmInculuidos;
    private double cargosKm;
    private double cargosEdad;
    private double descuento;
    private double cargoSeguro;
    private double total;

    public double suma(){
        return costoRenta + cargosKm + cargosEdad + cargoSeguro;
    };


}
