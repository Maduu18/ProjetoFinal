package com.Erp.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;
    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Formato de e-mail inválido")
    @Column(unique = true, nullable = false)
    private String email;
    @NotBlank(message = "A senha é obrigatória")
    @Size(min=6)
    private String senha;
    @NotNull(message = "O perfil é obrigatório")
    @Enumerated(EnumType.STRING)
    private Perfil perfil;
    private boolean ativo = true;
    @Column(unique = true, nullable = true, length = 20)
    private String matricula;
    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;
}