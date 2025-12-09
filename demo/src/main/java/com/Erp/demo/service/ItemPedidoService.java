package com.Erp.demo.service;
import com.Erp.demo.model.ItemPedido;
import com.Erp.demo.repository.ItemPedidoRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    // Criar ou atualizar item
    public ItemPedido salvarItem(ItemPedido itemPedido) {

        // Caso deseje garantir o cálculo automático do subtotal (opcional)
        if (itemPedido.getProduto() != null && itemPedido.getQuantidade() != null) {
            BigDecimal preco = itemPedido.getProduto().getPreco();
            if (preco != null) {
                itemPedido.setSubtotal(preco.multiply(BigDecimal.valueOf(itemPedido.getQuantidade())));
            }
        }

        return itemPedidoRepository.save(itemPedido);
    }

    // Listar todos
    public List<ItemPedido> listarTodos() {
        return itemPedidoRepository.findAll();
    }

    // Buscar por ID
    public Optional<ItemPedido> buscarPorId(Long id) {
        return itemPedidoRepository.findById(id);
    }

    // Deletar item
    public void deletar(Long id) {
        itemPedidoRepository.deleteById(id);
    }
}
