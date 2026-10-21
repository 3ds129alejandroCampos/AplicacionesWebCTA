package com.example.A8.ticket.model;


import com.example.A8.usuario.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name="Ticket")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 10,max = 50,message = "Valores fuera de rago")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    private Prioridad prioridad;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties("partidas")
    private Usuario usuario;


}
