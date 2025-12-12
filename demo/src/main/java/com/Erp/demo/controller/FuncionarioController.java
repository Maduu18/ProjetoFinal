package com.Erp.demo.controller;

import com.Erp.demo.model.Funcionario;
import com.Erp.demo.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    // POST /api/funcionarios
    @PostMapping
    public ResponseEntity<Funcionario> criarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario novoFuncionario = funcionarioService.criarFuncionario(funcionario);
        return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
    }

    // GET /api/funcionarios
    @GetMapping
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
        return ResponseEntity.ok(funcionarios);
    }

    // GET /api/funcionarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        return ResponseEntity.ok(funcionario);
    }

    // PUT /api/funcionarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioAtualizado) {
        Funcionario funcionario = funcionarioService.atualizarFuncionario(id, funcionarioAtualizado);
        return ResponseEntity.ok(funcionario);
    }

    // DELETE /api/funcionarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
}
}