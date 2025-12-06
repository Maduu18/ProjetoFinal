import React, { useState, useEffect, useMemo } from "react";
import axios from "axios";
import { Card, CardContent } from "../../components/ui/Card";
import { ResponsiveContainer, LineChart, Line, XAxis, YAxis, Tooltip, Legend } from "recharts";

export default function Dashboards() {
  const [fluxo, setFluxo] = useState([]);

  // Busca os dados do backend ao montar o componente
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

  // Processamento dos dados
  const resumo = useMemo(() => {
    const vendas = fluxo.filter(f => f.valor > 0).reduce((acc, f) => acc + f.valor, 0);
    const despesas = fluxo.filter(f => f.valor < 0).reduce((acc, f) => acc + f.valor, 0);
    const saldo = vendas + despesas;

    const mensal = {};
    fluxo.forEach(f => {
      const mes = new Date(f.data).toLocaleString("default", { month: "short" });
      if (!mensal[mes]) mensal[mes] = { mes, vendas: 0, despesas: 0 };
      if (f.valor > 0) mensal[mes].vendas += f.valor;
      else mensal[mes].despesas += Math.abs(f.valor);
    });

    return {
      vendas,
      despesas: Math.abs(despesas),
      saldo,
      grafico: Object.values(mensal),
    };
  }, [fluxo]);

  return (
    <div>
      <h2>📊 Dashboards em tempo real</h2>

      {/* Cards Resumo */}
      <div>
        <Card>
          <CardContent>
            <h3>💰 Vendas</h3>
            <p>R$ {resumo.vendas.toLocaleString()}</p>
          </CardContent>
        </Card>

        <Card>
          <CardContent>
            <h3>📉 Despesas</h3>
            <p>R$ {resumo.despesas.toLocaleString()}</p>
          </CardContent>
        </Card>

        <Card>
          <CardContent>
            <h3>📌 Saldo</h3>
            <p style={{ color: resumo.saldo >= 0 ? "green" : "red" }}>
              R$ {resumo.saldo.toLocaleString()}
            </p>
          </CardContent>
        </Card>
      </div>

      {/* Gráfico */}
      <Card>
        <CardContent>
          <h3>📈 Evolução Mensal</h3>
          <ResponsiveContainer width="100%" height={300}>
            <LineChart data={resumo.grafico}>
              <XAxis dataKey="mes" />
              <YAxis />
              <Tooltip />
              <Legend />
              <Line type="monotone" dataKey="vendas" stroke="#4ade80" strokeWidth={3} />
              <Line type="monotone" dataKey="despesas" stroke="#f87171" strokeWidth={3} />
            </LineChart>
          </ResponsiveContainer>
        </CardContent>
      </Card>
    </div>
  );
}
