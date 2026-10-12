package com.example.hospedaje.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHospedajeDTO {
    private double costoHospedaje;
    private double ajusteTemporada;
    private double costoDesayuno;
    private double costoEstacionamiento;
    private double descuentoLargaEstancia;
    private double subtotal;
    private double impuestoHospedaje;
    private double total;
}
