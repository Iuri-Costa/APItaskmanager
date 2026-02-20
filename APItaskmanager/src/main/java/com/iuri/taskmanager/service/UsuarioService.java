package com.iuri.taskmanager.service;

import com.iuri.taskmanager.model.Usuario;
import com.iuri.taskmanager.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario salvar(Usuario usuario) {
        return repo.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
