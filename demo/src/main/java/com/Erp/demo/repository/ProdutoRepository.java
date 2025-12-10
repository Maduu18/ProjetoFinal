package com.Erp.demo.repository;

import com.Erp.demo.model.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findAllByAtivoTrueAndEstoqueGreaterThan(int estoque);
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
