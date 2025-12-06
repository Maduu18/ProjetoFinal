import React, { useState } from "react";
import axios from "axios";

function FormEntradaSaida() {
  const [codigo, setCodigo] = useState("");
  const [descricao, setDescricao] = useState("");
  const [categoria, setCategoria] = useState("");
  const [precoVenda, setPrecoVenda] = useState("");
  const [custo, setCusto] = useState("");
  const [quantidade, setQuantidade] = useState("");
  const [mensagem, setMensagem] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    // Validações básicas
    if (!codigo || codigo.length > 50) {
      alert("Código do produto inválido (máx 50 caracteres)");
      return;
    }
    if (!descricao || descricao.length > 100) {
      alert("Descrição inválida (máx 100 caracteres)");
      return;
    }
    if (!categoria || categoria.length > 50) {
      alert("Categoria inválida (máx 50 caracteres)");
      return;
    }
    if (isNaN(precoVenda) || precoVenda <= 0) {
      alert("Preço de venda inválido");
      return;
    }
    if (isNaN(custo) || custo < 0) {
      alert("Custo inválido");
      return;
    }
    if (!Number.isInteger(Number(quantidade)) || quantidade < 0) {
      alert("Quantidade inválida");
      return;
    }

    const dados = {
      codigo_produto: codigo,
      descricao,
      categoria,
      preco_venda: parseFloat(precoVenda),
      custo: parseFloat(custo),
      quantidade_estoque: parseInt(quantidade, 10),
    };

    // Envia para o backend
    axios.post("http://localhost:8080/produtos", dados)
      .then((res) => {
        setMensagem("Produto enviado com sucesso!");
        setCodigo("");
        setDescricao("");
        setCategoria("");
        setPrecoVenda("");
        setCusto("");
        setQuantidade("");
      })
      .catch((err) => {
        console.error(err);
        setMensagem("Erro ao enviar o produto.");
      });
  };

  return (
    <div>
      <h2>📌 Entrada e Saída de Produtos</h2>
      {mensagem && <p>{mensagem}</p>}

      <form onSubmit={handleSubmit}>
        <div>
          <label>Código Produto:</label>
          <input
            type="text"
            value={codigo}
            maxLength={50}
            onChange={(e) => setCodigo(e.target.value)}
          />
        </div>

        <div>
          <label>Descrição:</label>
          <input
            type="text"
            value={descricao}
            maxLength={100}
            onChange={(e) => setDescricao(e.target.value)}
          />
        </div>

        <div>
          <label>Categoria:</label>
          <input
            type="text"
            value={categoria}
            maxLength={50}
            onChange={(e) => setCategoria(e.target.value)}
          />
        </div>

        <div>
          <label>Preço de Venda:</label>
          <input
            type="number"
            step="0.01"
            value={precoVenda}
            onChange={(e) => setPrecoVenda(e.target.value)}
          />
        </div>

        <div>
          <label>Custo:</label>
          <input
            type="number"
            step="0.01"
            value={custo}
            onChange={(e) => setCusto(e.target.value)}
          />
        </div>

        <div>
          <label>Quantidade em Estoque:</label>
          <input
            type="number"
            value={quantidade}
            onChange={(e) => setQuantidade(e.target.value)}
          />
        </div>

        <button type="submit">Enviar</button>
      </form>
    </div>
  );
}

export default FormEntradaSaida;
