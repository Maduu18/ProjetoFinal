// Inventario.js
import React, { useState, useEffect } from "react";
import axios from "axios";

export default function Inventario() {
  const [produtos, setProdutos] = useState([]);
  const baseURL = "http://localhost:8080/produtos"; // ajuste conforme seu backend

  useEffect(() => {
    axios.get(baseURL)
      .then(res => setProdutos(res.data))
      .catch(err => console.error("Erro ao buscar produtos:", err));
  }, []);

  const handleEdit = (codigo_produto) => {
    // Implemente a lógica de edição aqui.
    // Exemplo: redirecionar para uma página de formulário de edição
    // ou abrir um modal.
    console.log(`Editar produto com código: ${codigo_produto}`);
  };

  const handleRemove = (codigo_produto) => {
    if (window.confirm("Tem certeza que deseja remover este produto?")) {
      axios.delete(`${baseURL}/${codigo_produto}`)
        .then(() => {
          setProdutos(produtos.filter(p => p.codigo_produto !== codigo_produto));
          console.log(`Produto com código ${codigo_produto} removido.`);
        })
        .catch(err => console.error("Erro ao remover produto:", err));
    }
  };

  return (
    <div>
      <h2>📌 Inventário de Produtos</h2>
      <table border="1" cellPadding="5" cellSpacing="0">
        <thead>
          <tr>
            <th>Código</th>
            <th>Descrição</th>
            <th>Categoria</th>
            <th>Preço de Venda</th>
            <th>Custo</th>
            <th>Quantidade em Estoque</th>
            <th>Editar</th>
            <th>Remover</th>
          </tr>
        </thead>
        <tbody>
          {produtos.length === 0 ? (
            <tr><td colSpan="8" style={{ textAlign: "center" }}>Nenhum produto cadastrado.</td></tr>
          ) : (
            produtos.map((produto) => (
              <tr key={produto.codigo_produto}>
                <td>{produto.codigo_produto}</td>
                <td>{produto.descricao}</td>
                <td>{produto.categoria}</td>
                <td>{produto.preco_venda.toFixed(2)}</td>
                <td>{produto.custo.toFixed(2)}</td>
                <td>{produto.quantidade_estoque}</td>
                <td>
                  <button onClick={() => handleEdit(produto.codigo_produto)}>
                    ✏️
                  </button>
                </td>
                <td>
                  <button onClick={() => handleRemove(produto.codigo_produto)}>
                    🗑️
                  </button>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}