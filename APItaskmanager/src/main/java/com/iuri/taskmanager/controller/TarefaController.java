package com.iuri.taskmanager.controller;

import com.iuri.taskmanager.dto.TarefaRequest;
import com.iuri.taskmanager.model.StatusTarefa;
import com.iuri.taskmanager.model.Tarefa;
import com.iuri.taskmanager.service.TarefaService;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @PostMapping
    public Tarefa criar(@RequestBody TarefaRequest dto,
                        @RequestParam Long usuarioId) {
        return service.criar(dto, usuarioId);
    }


    @GetMapping("/usuario/{id}")
    public List<Tarefa> listar(@PathVariable Long id) {
        return service.listarPorUsuario(id);
    }

    @PatchMapping("/{id}/status")
    public Tarefa atualizarStatus(@PathVariable Long id,
                                  @RequestParam StatusTarefa status) {
        return service.atualizarStatus(id, status);
    }
}
