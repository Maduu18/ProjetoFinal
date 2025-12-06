import React, { useState, useEffect } from "react";
import axios from "axios";

export default function Gerenciais() {
  const [fluxo, setFluxo] = useState([]);

  useEffect(() => {
    const fetchFluxo = async () => {
      try {
        const res = await axios.get("http://localhost:8080/fluxos");
        setFluxo(res.data);
      } catch (err) {
        console.error("Erro ao buscar fluxo de caixa:", err);
        setFluxo([]); // fallback caso dê erro
      }
    };
    fetchFluxo();
  }, []);

  return (
    <div>
      <h2>📌 Relatórios Gerenciais e Operacionais</h2>

      {fluxo.length === 0 ? (
        <p>Nenhum registro de fluxo de caixa disponível.</p>
      ) : (
        <table border="1" cellPadding="5" cellSpacing="0">
          <thead>
            <tr>
              <th>ID Conta</th>
              <th>Data</th>
              <th>Valor</th>
              <th>Forma de Pagamento</th>
              <th>Tipo</th>
            </tr>
          </thead>
          <tbody>
            {fluxo.map((f, index) => (
              <tr key={index} style={{ backgroundColor: index % 2 === 0 ? "#fff" : "#f9f9f9" }}>
                <td>{f.id_conta}</td>
                <td>{f.data}</td>
                <td style={{ color: f.valor >= 0 ? "green" : "red" }}>
                  R$ {f.valor.toLocaleString()}
                </td>
                <td>{f.forma_pagamento}</td>
                <td>{f.valor >= 0 ? "Venda" : "Despesa"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}
