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
public class InteracaoCliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idInteracao;
    private String descricao;
    private String canal;
public InteracaoCliente(long idInteracao, String descricao, String canal){
this.idInteracao = idInteracao;
this.descricao = descricao;
this.canal = canal;
    }
}
