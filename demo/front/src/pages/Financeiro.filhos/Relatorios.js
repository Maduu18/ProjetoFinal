import React, { useEffect, useState } from "react";
import axios from "axios";

export default function Relatorios() {
  const [fluxos, setFluxos] = useState([]);
  const baseURL = "http://localhost:8080/fluxo"; // ajuste conforme sua API

  useEffect(() => {
    axios.get(baseURL)
      .then(res => setFluxos(res.data))
      .catch(err => console.error("Erro ao buscar fluxos:", err));
  }, []);

  const total = fluxos.reduce((acc, f) => acc + f.valor, 0);

  return (
    <div>
      <h2>📊 Relatórios Financeiros - Fluxo de Caixa</h2>

      {fluxos.length === 0 ? (
        <p>Nenhum registro de fluxo de caixa cadastrado.</p>
      ) : (
        <>
          <table border="1" cellPadding="5" cellSpacing="0">
            <thead>
              <tr>
                <th>ID Conta</th>
                <th>Data</th>
                <th>Valor</th>
                <th>Forma de Pagamento</th>
              </tr>
            </thead>
            <tbody>
              {fluxos.map((f, index) => (
                <tr key={index}>
                  <td>{f.id_conta}</td>
                  <td>{f.data}</td>
                  <td>{f.valor.toFixed(2)}</td>
                  <td>{f.forma_pagamento}</td>
                </tr>
              ))}
            </tbody>
          </table>

          <h3>Total: R$ {total.toFixed(2)}</h3>
        </>
      )}
    </div>
  );
}
