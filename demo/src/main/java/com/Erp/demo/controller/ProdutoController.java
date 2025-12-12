package com.Erp.demo.controller;

import com.Erp.demo.exception.EstoqueInsuficienteException;
import com.Erp.demo.model.Produto;
import com.Erp.demo.service.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    // Classe interna para o corpo da requisição de ajuste de estoque
    private static class AjusteEstoqueRequest {
        public Integer quantidade;
    }

    // POST /api/produtos
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.criarProduto(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    // GET /api/produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos); }

    // GET /api/produtos/disponiveis
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Produto>> listarProdutosDisponiveis() {
        List<Produto> produtos = produtoService.listarProdutosDisponiveis();
        return ResponseEntity.ok(produtos);
    }

    // GET /api/produtos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    // PUT /api/produtos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Produto produto = produtoService.atualizarProduto(id, produtoAtualizado);
        return ResponseEntity.ok(produto);
    }

    // PATCH /api/produtos/{id}/desativar
    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativarProduto(@PathVariable Long id) {
        produtoService.desativarProduto(id);
        return ResponseEntity.noContent().build();
    }
    
    // PATCH /api/produtos/{id}/baixar-estoque
    @PatchMapping("/{id}/baixar-estoque")
    public ResponseEntity<Produto> baixarEstoque(@PathVariable Long id, @RequestBody AjusteEstoqueRequest request) {
        Produto produtoAtualizado = produtoService.baixarEstoque(id, request.quantidade);
        return ResponseEntity.ok(produtoAtualizado);
    }
    
    // PATCH /api/produtos/{id}/repor-estoque
    @PatchMapping("/{id}/repor-estoque")
    public ResponseEntity<Produto> reporEstoque(@PathVariable Long id, @RequestBody AjusteEstoqueRequest request) {
        Produto produtoAtualizado = produtoService.reporEstoque(id, request.quantidade);
        return ResponseEntity.ok(produtoAtualizado); 
    }

    // Gerenciamento de Exceções local (pode ser movido para um @ControllerAdvice global)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleEstoqueInsuficienteException(EstoqueInsuficienteException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}