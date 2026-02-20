package com.iuri.taskmanager.repository;

import com.iuri.taskmanager.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
