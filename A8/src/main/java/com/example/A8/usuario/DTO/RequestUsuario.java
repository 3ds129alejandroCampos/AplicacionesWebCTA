package com.example.A8.usuario.DTO;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUsuario {


    @Size(min = 3,max = 40,message = "Longitud de nombre fuera de lo permitido")
    String nombre;

}
