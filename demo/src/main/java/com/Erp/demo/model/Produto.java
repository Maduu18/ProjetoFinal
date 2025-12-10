package com.Erp.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    @NotBlank(message = "Nome do produto é obrigatório")
    @Column(length = 100, nullable = false)
    private String nome;

    private String descricao;

    @NotNull
    @Positive(message = "O preço deve ser maior que zero")
    @Column(precision = 10, scale = 2)
    private BigDecimal preco;

    @NotNull
    @Min(value = 0, message = "O estoque não pode ser negativo")
    private Integer estoque;

    private String imagemUrl;
    private boolean ativo = true;
    private LocalDateTime dataCadastro;
}