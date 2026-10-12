package com.example.rentaVehiculos.service;

import com.example.rentaVehiculos.DTO.renta.RentaRequest;
import com.example.rentaVehiculos.DTO.renta.ResponseRentaDTO;
import org.springframework.stereotype.Service;

@Service
public class RentaVehiculosService {

    public ResponseRentaDTO calcularRenta(RentaRequest payload) {
        double costoDiario = switch (payload.getTipoVehiculo().toUpperCase()) {
            case "COMPACTO" -> 550.0;
            case "SEDAN" -> 700.0;
            case "SUV" -> 950.0;
            case "CAMIONETA" -> 1200.0;
            default -> 0.0;
        };

        double costoRenta = costoDiario * payload.getDiasRenta();
        int kmIncluidos = payload.getDiasRenta() * 100;

        double cargosKm = 0.0;
        if (payload.getKilometrosEstimados() > kmIncluidos) {
            cargosKm = (payload.getKilometrosEstimados() - kmIncluidos) * 4.0;
        }

        double cargosEdad = 0.0;
        if (payload.getEdad() >= 18 && payload.getEdad() <= 24) {
            cargosEdad = (costoRenta + cargosKm) * 0.15;
        }

        double cargoSeguro = 0.0;
        if (Boolean.TRUE.equals(payload.isSeguroCompleto())) {
            cargoSeguro = 180.0 * payload.getDiasRenta();
        }


        double descuento = 0.0;
        if (payload.getDiasRenta() >= 7) {
            descuento = costoRenta * 0.10;
        }

        double total = (costoRenta - descuento) + cargosKm + cargosEdad + cargoSeguro;

        ResponseRentaDTO response = new ResponseRentaDTO();
        response.setCostoRenta(costoRenta);
        response.setKmInculuidos(kmIncluidos);
        response.setCargosKm(cargosKm);
        response.setCargosEdad(cargosEdad);
        response.setCargoSeguro(cargoSeguro);
        response.setDescuento(descuento);
        response.setTotal(total);

        return response;
    }
}
