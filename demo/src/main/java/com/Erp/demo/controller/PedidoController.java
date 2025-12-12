package com.Erp.demo.controller;

import com.Erp.demo.model.Pedido;
import com.Erp.demo.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    // Injeção de dependência via construtor
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // GET /api/pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }

    // GET /api/pedidos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);
        
        // Retorna 200 OK com o pedido ou 404 Not Found
        return pedido.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // NOTA: Métodos POST, PUT e DELETE não foram incluídos
    // pois o PedidoService fornecido não possui as operações
    // salvar, atualizar ou deletar.
}