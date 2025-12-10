package com.Erp.demo.service;
import com.Erp.demo.exception.EstoqueInsuficienteException;
import com.Erp.demo.model.Produto;
import com.Erp.demo.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    // Criar produto
    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Listar todos os produtos
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    // Buscar produto por ID
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + id));
    }

    @Transactional
    public Produto atualizarProduto(Long id, Produto novoProduto) {
        Produto produtoExistente = buscarPorId(id);

        if (novoProduto.getNome() != null && !novoProduto.getNome().isBlank()) {
            produtoExistente.setNome(novoProduto.getNome());
        }
        if (novoProduto.getDescricao() != null) {
            produtoExistente.setDescricao(novoProduto.getDescricao());
        }
        if (novoProduto.getPreco() != null) {
            produtoExistente.setPreco(novoProduto.getPreco());
        }
        if (novoProduto.getEstoque() != null) {
             produtoExistente.setEstoque(novoProduto.getEstoque());
        }
        if (novoProduto.getImagemUrl() != null) {
            produtoExistente.setImagemUrl(novoProduto.getImagemUrl());
        }

        return produtoRepository.save(produtoExistente);
    }

    // Deletar produto
    public void desativarProduto(Long id) {
        Produto produto = buscarPorId(id);
        produto.setAtivo(false);
        produtoRepository.save(produto);
    }
    
    @Transactional 
    public Produto baixarEstoque(Long idProduto, Integer quantidade) {
        Produto produto = buscarPorId(idProduto);

        if (produto.getEstoque() < quantidade) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + produto.getNome());
        }

        int novoEstoque = produto.getEstoque() - quantidade;
        produto.setEstoque(novoEstoque);

        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutosDisponiveis() {
        return produtoRepository.findAllByAtivoTrueAndEstoqueGreaterThan(0);
    }

    @Transactional
    public Produto reporEstoque(Long idProduto, Integer quantidade) {
        Produto produto = buscarPorId(idProduto);

        int novoEstoque = produto.getEstoque() + quantidade;
        produto.setEstoque(novoEstoque);

        return produtoRepository.save(produto);
    }

}
