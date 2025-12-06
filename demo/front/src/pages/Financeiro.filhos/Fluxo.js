import React, { useState, useEffect } from "react";
import axios from "axios";

export default function Fluxo({ fluxos, setFluxos }) {
  const [idConta, setIdConta] = useState("");
  const [data, setData] = useState("");
  const [valor, setValor] = useState("");
  const [formaPagamento, setFormaPagamento] = useState("");

  const baseURL = "http://localhost:8080/fluxo"; // ajuste conforme sua API

  // Busca fluxos ao carregar a página
  useEffect(() => {
    axios.get(baseURL)
      .then(res => setFluxos(res.data))
      .catch(err => console.error("Erro ao buscar fluxos:", err));
  }, [setFluxos]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!idConta) return alert("Informe o ID da conta.");
    if (!data) return alert("Informe a data.");
    if (!valor || isNaN(valor)) return alert("Informe um valor válido.");
    if (!formaPagamento) return alert("Informe a forma de pagamento.");

    const novoFluxo = {
      id_conta: idConta,
      data,
      valor: parseFloat(valor),
      forma_pagamento: formaPagamento,
    };

    try {
      const res = await axios.post(baseURL, novoFluxo);
      setFluxos([...fluxos, res.data]); // adiciona registro retornado pelo backend
      setIdConta("");
      setData("");
      setValor("");
      setFormaPagamento("");
    } catch (err) {
      console.error("Erro ao adicionar fluxo:", err);
      alert("Falha ao adicionar fluxo.");
    }
  };

  return (
    <div>
      <h2>📌 Fluxo de Caixa</h2>

      <form onSubmit={handleSubmit} style={{ marginBottom: "20px" }}>
        <div>
          <label>ID Conta:</label>
          <input type="text" value={idConta} onChange={(e) => setIdConta(e.target.value)} />
        </div>

        <div>
          <label>Data:</label>
          <input type="date" value={data} onChange={(e) => setData(e.target.value)} />
        </div>

        <div>
          <label>Valor:</label>
          <input type="number" step="0.01" value={valor} onChange={(e) => setValor(e.target.value)} />
        </div>

        <div>
          <label>Forma de Pagamento:</label>
          <input type="text" value={formaPagamento} onChange={(e) => setFormaPagamento(e.target.value)} />
        </div>

        <button type="submit">Adicionar</button>
      </form>
    </div>
  );
}
