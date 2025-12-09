package com.Erp.demo.service;
import com.Erp.demo.model.Pedido;
import com.Erp.demo.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // Criar ou atualizar pedido
    public Pedido salvarPedido(Pedido pedido) {
        // Garante que o relacionamento bidirecional esteja consistente
        if (pedido.getItens() != null) {
            pedido.getItens().forEach(item -> item.setPedido(pedido));
        }

        return pedidoRepository.save(pedido);
    }

    // Listar todos
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    // Buscar por ID
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    // D
