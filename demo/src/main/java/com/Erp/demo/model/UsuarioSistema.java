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
public class UsuarioSistema {
	@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idUsuario;
    private String nomeUsuario;
    private String senha;
    private String email;

public UsuarioSistema(long idUsuario, String nomeUsuario, String email, String senha){
this.idUsuario = idUsuario;
this.nomeUsuario = nomeUsuario;
this.senha = senha;
this.email = email;
    }
}