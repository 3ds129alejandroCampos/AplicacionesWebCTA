package com.example.tienda.DTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductoDto {

    @NotBlank
    private String nombreProducto;

    @Min(value = 1,message = "Precio invalido")
    private double precio;

    @Min(value = 1,message = "Valor debajo del rango minimo (1)")
    private int stock;
}
