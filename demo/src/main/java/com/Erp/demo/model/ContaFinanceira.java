package com.Erp.demo.model;

import com.google.appengine.repackaged.org.joda.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ContaFinanceira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idConta;
    private String descricao;
    private double valor;
    private String tipo;
	private LocalDate dataVencimento;

    public ContaFinanceira(long idConta, String descricao, double valor, String tipo, LocalDate dataVencimento) {
        this.idConta = idConta;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.dataVencimento = dataVencimento;
    }

    public ContaFinanceira() {
    }

}
