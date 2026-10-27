package com.example.A8.usuario.service;

import com.example.A8.exception.ResourceNotFoundException;
import com.example.A8.usuario.DTO.RequestUsuario;
import com.example.A8.usuario.model.Usuario;
import com.example.A8.usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(RequestUsuario nuevo) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nuevo.getNombre());
        usuario.setActivo(true);
        return usuarioRepository.save(usuario);
    }

    public Usuario desactivarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        usuario.setActivo(false);
        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }
}
