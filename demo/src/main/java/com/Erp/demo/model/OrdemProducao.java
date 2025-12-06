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
public class OrdemProducao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idOrdem;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private int quantidadeProduzir;
	
public OrdemProducao(long idOrdem, LocalDate dataInicio, LocalDate dataFim, int quantidadeProduzir) {
	this.idOrdem = idOrdem;
	this.dataInicio = dataInicio;
	this.dataFim = dataFim;
	this.quantidadeProduzir = quantidadeProduzir;
	
	}
}
