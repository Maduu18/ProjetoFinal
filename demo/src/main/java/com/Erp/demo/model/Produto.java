package com.Erp.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

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
    private String nome;

    private String descricao;

    @NotNull
    @Positive(message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull
    @Min(value = 0, message = "O estoque não pode ser negativo")
    private Integer estoque;

    private String imagemUrl;
}