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
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFuncionario;
    private String nome;
    private int cpf;
    private String cargo;
    private Double salario;

public Funcionario (long idFuncionario, String nome, int cpf, String cargo, Double salario){
    this.idFuncionario = idFuncionario;
    this.nome = nome;
    this.cpf = cpf;
    this.cargo = cargo;
    this.salario = salario;   
    }
}
