package com.example.demo.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPrestamoNuevo {

    @NotNull(message = "Tienes que enviar el id del lector")
    Long idLector;

    @NotNull(message = "Tienes que enviar el id del libro")
    Long idLibro;
}
