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
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFornecedor;
    private String razaoSocial;
    private String cnpj;
    private String endereco;
    private String contato;

    public Fornecedor() {}

    public Fornecedor(long idFornecedor, String razaoSocial, String cnpj, String endereco, String contato) {
        this.idFornecedor = idFornecedor;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.contato = contato;
    }
}
