package com.Erp.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @NotNull
    private LocalDateTime data = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @NotNull(message = "Forma de pagamento obrigatória")
    private String formaPagamento;

    private String codigoRetirada;

    @ManyToOne
    @JoinColumn(name = "id_aluno") // FK para Cliente
    @NotNull
    private Cliente cliente;

    // Relacionamento com Itens (Composição)
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @NotEmpty(message = "O pedido deve ter pelo menos um item")
    private List<ItemPedido> itens;
    
    // Método auxiliar para adicionar itens e manter a consistência bidirecional
    public void adicionarItem(ItemPedido item) {
        item.setPedido(this);
        this.itens.add(item);
    }
}