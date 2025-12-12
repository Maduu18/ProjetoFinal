package com.Erp.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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
    private FormaPagamento formaPagamento;

    private String codigoRetirada;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    @NotNull
    private Cliente cliente;

    public enum StatusPedido {
    EM_PREPARACAO,
    PRONTO_PARA_RETIRADA,
    ENTREGUE,
    CANCELADO
    }

    public enum FormaPagamento {
        PIX,
        CARTAO,
        DINHEIRO
    }


}