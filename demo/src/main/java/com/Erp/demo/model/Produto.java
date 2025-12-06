package com.Erp.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produto {

	@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idProduto;
    private String codigoProduto;
    private String descricao;
    private String categoria;
    private double precoVenda;
    private double custo;
    private int quantidadeEstoque;

    public Produto() {
    }

    public Produto(long idProduto, String codigoProduto, String descricao, String categoria, double precoVenda, double custo, int quantidadeEstoque) {
        this.idProduto = idProduto;
        this.codigoProduto = codigoProduto;
        this.descricao = descricao;
        this.categoria = categoria;
        this.precoVenda = precoVenda;
        this.custo = custo;
        this.quantidadeEstoque = quantidadeEstoque;
    }
}
