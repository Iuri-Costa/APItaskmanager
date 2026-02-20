package com.iuri.taskmanager.service;

import com.iuri.taskmanager.dto.TarefaRequest;
import com.iuri.taskmanager.model.*;
import com.iuri.taskmanager.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository repo;
    private final UsuarioService usuarioService;

    public TarefaService(TarefaRepository repo, UsuarioService usuarioService) {
        this.repo = repo;
        this.usuarioService = usuarioService;
    }

    public Tarefa criar(TarefaRequest dto, Long usuarioId) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setUsuario(usuario);
        tarefa.setStatus(StatusTarefa.PENDENTE);
        tarefa.setDataCriacao(LocalDateTime.now());

        return repo.save(tarefa);
    }


    public List<Tarefa> listarPorUsuario(Long usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }

    public Tarefa atualizarStatus(Long tarefaId, StatusTarefa novoStatus) {
        Tarefa tarefa = repo.findById(tarefaId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        if (tarefa.getStatus() == StatusTarefa.PENDENTE
                && novoStatus == StatusTarefa.CONCLUIDA) {
            throw new RuntimeException("Fluxo inválido");
        }

        tarefa.setStatus(novoStatus);
        return repo.save(tarefa);
    }
}
