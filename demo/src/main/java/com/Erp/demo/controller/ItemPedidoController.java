package com.Erp.demo.controller;

import com.Erp.demo.model.ItemPedido;
import com.Erp.demo.service.ItemPedidoService;
import lombok.RequiredArgsConstructor; // Importar Lombok
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itens-pedido")
@RequiredArgsConstructor // Usar esta anotação para injeção de dependência
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService; // Marcar como final

    // O construtor explícito foi removido, pois @RequiredArgsConstructor o gera

    // POST /api/itens-pedido
    @PostMapping
    public ResponseEntity<ItemPedido> criarItemPedido(@RequestBody ItemPedido itemPedido) {
        ItemPedido novoItem = itemPedidoService.salvarItem(itemPedido);
        return new ResponseEntity<>(novoItem, HttpStatus.CREATED);
    }

    // GET /api/itens-pedido
    @GetMapping
    public ResponseEntity<List<ItemPedido>> listarItensPedido() {
        List<ItemPedido> itens = itemPedidoService.listarTodos();
        return ResponseEntity.ok(itens);
    }

    // GET /api/itens-pedido/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> buscarItemPedidoPorId(@PathVariable Long id) {
        Optional<ItemPedido> item = itemPedidoService.buscarPorId(id);
        
        return item.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT /api/itens-pedido/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ItemPedido> atualizarItemPedido(@PathVariable Long id, @RequestBody ItemPedido itemPedidoAtualizado) {
        // Primeiro, verifica se o item existe. Se não, retorna 404
        if (itemPedidoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        // Define o ID no objeto para garantir que o JPA atualize a entidade existente
        itemPedidoAtualizado.setIdItem(id);
        
        ItemPedido itemAtualizado = itemPedidoService.salvarItem(itemPedidoAtualizado);
        return ResponseEntity.ok(itemAtualizado);
    }

    // DELETE /api/itens-pedido/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItemPedido(@PathVariable Long id) {
        // É importante notar que deletar por ID sem checagem de existência pode ser OK,
        // mas se for necessário retornar 404 caso o item não exista, seria necessário
        // buscar o item antes (e capturar uma possível exceção de não encontrado).
        itemPedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}