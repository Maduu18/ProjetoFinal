package com.Erp.demo.service;
import com.Erp.demo.model.Produto;
import com.Erp.demo.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
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

    // Atualizar produto
    public Produto atualizarProduto(Long id, Produto novoProduto) {
        Produto produtoExistente = buscarPorId(id);

        produtoExistente.setNome(novoProduto.getNome());
        produtoExistente.setDescricao(novoProduto.getDescricao());
        produtoExistente.setPreco(novoProduto.getPreco());
        produtoExistente.setEstoque(novoProduto.getEstoque());
        produtoExistente.setImagemUrl(novoProduto.getImagemUrl());

        return produtoRepository.save(produtoExistente);
    }

    // Deletar produto
    public void deletarProduto(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }
}
