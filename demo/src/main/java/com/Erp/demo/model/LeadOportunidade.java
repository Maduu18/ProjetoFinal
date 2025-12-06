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
public class LeadOportunidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLead;
    private String nome;
    private String email;
    private String origem;
    private String status;

public LeadOportunidade(long idLead, String nome, String email, String origem, String status){
this.idLead = idLead;
this.nome = nome;
this.email = email;
this.origem = origem;
this.status = status;    
    }
}
