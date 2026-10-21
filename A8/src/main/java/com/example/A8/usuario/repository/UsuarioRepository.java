package com.example.A8.usuario.repository;

import com.example.A8.usuario.model.Usuario;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
