package com.example.A8.controller.usuario;


import com.example.A8.usuario.DTO.ResponseUsuario;
import com.example.A8.usuario.DTO.RequestUsuario;
import com.example.A8.usuario.model.Usuario;
import com.example.A8.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody RequestUsuario nuevoUser){
        Usuario usuario = usuarioService.crearUsuario(nuevoUser);
        return ResponseEntity.status(201).body(usuario);
    }
}
