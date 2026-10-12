package com.example.hospedaje.service;

import com.example.hospedaje.DTO.HospedajeRequest;
import com.example.hospedaje.DTO.ResponseHospedajeDTO;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {

    public ResponseHospedajeDTO calcularHospedaje(HospedajeRequest payload) {


        int capacidadMaxima = switch (payload.getTipoHabitacion().toUpperCase()) {
            case "INDIVIDUAL" -> 1;
            case "DOBLE" -> 2;
            case "SUITE" -> 4;
            default -> throw new IllegalArgumentException("Tipo de habitacion no válido.");
        };

        if (payload.getNumeroHuespedes() > capacidadMaxima) {
            throw new IllegalArgumentException(
                    "El número de huéspedes (" + payload.getNumeroHuespedes() + ") supera la capacidad maxima de la habitación " + payload.getTipoHabitacion() + " (" + capacidadMaxima + " máx)."
            );
        }
        double costoPorNoche = switch (payload.getTipoHabitacion().toUpperCase()) {
            case "INDIVIDUAL" -> 700.0;
            case "DOBLE" -> 1100.0;
            case "SUITE" -> 1800.0;
            default -> 0.0;
        };

        double costoHospedaje = costoPorNoche * payload.getNumeroNoches();

        double ajusteTemporada = switch (payload.getTemporada().toUpperCase()) {
            case "BAJA" -> -(costoHospedaje * 0.10);
            case "ALTA" -> costoHospedaje * 0.25;
            default -> 0.0;
        };

        double costoDesayuno = 0.0;
        if (payload.isIncluyeDesayuno()) {
            costoDesayuno = payload.getNumeroHuespedes() * payload.getNumeroNoches() * 150.0;
        }

        double costoEstacionamiento = 0.0;
        if (payload.isIncluyeEstacionamiento()) {
            costoEstacionamiento = payload.getNumeroNoches() * 100.0;
        }

        double descuentoLargaEstancia = 0.0;
        if (payload.getNumeroNoches() >= 7) {
            descuentoLargaEstancia = costoHospedaje * 0.08;
        }

        double subtotal = (costoHospedaje + ajusteTemporada - descuentoLargaEstancia) + costoDesayuno + costoEstacionamiento;
        double impuestoHospedaje = subtotal * 0.04;

        double total = subtotal + impuestoHospedaje;

        ResponseHospedajeDTO response = new ResponseHospedajeDTO();
        response.setCostoHospedaje(costoHospedaje);
        response.setAjusteTemporada(ajusteTemporada);
        response.setCostoDesayuno(costoDesayuno);
        response.setCostoEstacionamiento(costoEstacionamiento);
        response.setDescuentoLargaEstancia(descuentoLargaEstancia);
        response.setSubtotal(subtotal);
        response.setImpuestoHospedaje(impuestoHospedaje);
        response.setTotal(total);

        return response;
    }
}
