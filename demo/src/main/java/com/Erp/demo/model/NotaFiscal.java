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
public class NotaFiscal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idNota;
    private String numeroNota;
    private double valorTotal;
    private LocalDate dataEmissao;
    private String formaPagamento;
    private long idVenda;

    public NotaFiscal() {
    }

    public NotaFiscal(long idNota, String numeroNota, double valorTotal, LocalDate dataEmissao, String formaPagamento, long idVenda) {
        this.idNota = idNota;
        this.numeroNota = numeroNota;
        this.valorTotal = valorTotal;
        this.dataEmissao = dataEmissao;
        this.formaPagamento = formaPagamento;
        this.idVenda = idVenda;
    }
}
