package com.example.A8.usuario.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseUsuario {
    private Long id;
    private String nombre;
    private boolean activo;
}
