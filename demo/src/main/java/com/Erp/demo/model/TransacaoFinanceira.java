package com.Erp.demo.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TransacaoFinanceira {

	@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idTransacao;
    private long idConta;
    private LocalDate data;
    private double valor;
    private String formaPagamento;

    public TransacaoFinanceira() {
    }

    public TransacaoFinanceira(long idTransacao, long idConta, LocalDate data, double valor, String formaPagamento) {
        this.idTransacao = idTransacao;
        this.idConta = idConta;
        this.data = data;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
    }
}
