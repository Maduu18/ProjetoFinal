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
public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDepartamento;
    private String nome;
    private String localizacao;

public Departamento(long idDepartamento, String nome, String locaizacao){
    this.idDepartamento = idDepartamento;
    this.nome = nome;
    this.localizacao = locaizacao;
    }
}
