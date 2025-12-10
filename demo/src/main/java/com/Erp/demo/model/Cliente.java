package com.Erp.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno")
    private Long idAluno;

    @NotBlank
    private String nome;

    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter DDD e número")
    private String telefone;

   @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario")
    @NotNull(message = "O cliente precisa de um usuário vinculado")
    private Usuario usuario;
}