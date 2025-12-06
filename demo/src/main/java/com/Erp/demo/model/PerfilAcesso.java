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
public class PerfilAcesso {
	@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idPerfil;
    private String nome;
    private String descricao;

public PerfilAcesso(long idPerfil, String nome, String descricao){
this.idPerfil = idPerfil;
this.nome = nome;
this.descricao = descricao;
}
}
