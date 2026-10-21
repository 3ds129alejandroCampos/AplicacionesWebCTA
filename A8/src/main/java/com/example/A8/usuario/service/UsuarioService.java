package com.example.A8.usuario.service;

import com.example.A8.usuario.DTO.RequestUsuario;
import com.example.A8.usuario.DTO.ResponseUsuario;
import com.example.A8.usuario.model.Usuario;
import com.example.A8.usuario.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(RequestUsuario nuevo){
        Usuario usuarioN= new Usuario();
        usuarioN.setNombre(nuevo.getNombre());
        usuarioN.setActivo(true);
        return usuarioRepository.save(usuarioN);
    }
}
