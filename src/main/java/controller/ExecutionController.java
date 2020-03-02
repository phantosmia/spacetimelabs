package controller;

import models.Execution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.ExecutionItemRepository;
import repositories.ExecutionRepository;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ExecutionController {
    private ExecutionItemRepository executionItemRepository;
    private ExecutionRepository executionRepository;
    @GetMapping(path = "/executions/page")
    Page<Execution> carregarExecucoes(@RequestParam(defaultValue = "0") Integer numeroPagina,
                                      @RequestParam(defaultValue = "10") Integer quantidadePagina) {
        Pageable paging = PageRequest.of(numeroPagina, quantidadePagina);
        return executionRepository.findAll(paging);
    }
    @GetMapping(path="/execution/{id}")
    Optional<Execution> carregarExecution(@PathVariable Long idExecucao){
        Optional<Execution> execution = executionRepository.findById(idExecucao);
        return execution;
    }
    @PostMapping(path="/createexecution")
    ResponseEntity <Execution criarExecucao(@Valid @RequestBody Execution execution){
        Execution novaExecucao = new Execution(execution.getIdentifier(), execution.getInputs(), execution.getOutputs(), execution.getCreatedAt(), execution.getExecutions());
        novaExecucao = executionRepository.save(novaExecucao);
        return ResponseEntity.ok(novaExecucao);
    }
}
