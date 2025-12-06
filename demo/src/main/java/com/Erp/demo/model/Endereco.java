package com.Erp.demo.model;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEndereco;
	private String rua;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	
public Endereco(long idEndereco, String rua, int numero, String bairro, String cidade, String estado, String cep) {
	this.idEndereco = idEndereco;
	this.rua = rua;
	this.numero = numero;
	this.bairro = bairro;
	this.cidade = cidade;
	this.estado = estado;
	this.cep = cep;
	}
}
