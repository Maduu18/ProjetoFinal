// Fornecedores.js
import React, { useState, useEffect } from "react";
import axios from "axios";

export default function Fornecedores() {
  const [fornecedores, setFornecedores] = useState([]);
  const [razaoSocial, setRazaoSocial] = useState("");
  const [cnpj, setCnpj] = useState("");
  const [endereco, setEndereco] = useState("");
  const [contato, setContato] = useState("");
  const [fornecedorEditando, setFornecedorEditando] = useState(null); // Estado para gerenciar a edição

  const baseURL = "http://localhost:8080/fornecedores";

  // Função para buscar os fornecedores da API
  const fetchFornecedores = async () => {
    try {
      const res = await axios.get(baseURL);
      setFornecedores(res.data);
    } catch (err) {
      console.error("Erro ao buscar fornecedores:", err);
    }
  };

  // Busca fornecedores ao carregar a página
  useEffect(() => {
    fetchFornecedores();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!razaoSocial) return alert("Informe a razão social.");
    if (!cnpj || (cnpj.length !== 14 && cnpj.length !== 11)) return alert("CNPJ inválido (11 ou 14 dígitos).");
    if (!endereco) return alert("Informe o endereço.");
    if (!contato) return alert("Informe o contato.");

    const dados = { razao_social: razaoSocial, cnpj, endereco, contato };

    try {
      if (fornecedorEditando) {
        // Lógica para EDIÇÃO (PUT)
        await axios.put(`${baseURL}/${fornecedorEditando.fornecedor_id}`, dados);
        alert("Fornecedor atualizado com sucesso!");
        setFornecedorEditando(null); // Sai do modo de edição
      } else {
        // Lógica para CADASTRO (POST)
        await axios.post(baseURL, dados);
        alert("Fornecedor adicionado com sucesso!");
      }

      // Limpa os campos e atualiza a lista
      setRazaoSocial("");
      setCnpj("");
      setEndereco("");
      setContato("");
      fetchFornecedores();
    } catch (err) {
      console.error("Erro ao processar fornecedor:", err);
      alert("Falha ao processar fornecedor.");
    }
  };

  const handleEdit = (fornecedor) => {
    // Preenche o formulário com os dados do fornecedor para edição
    setRazaoSocial(fornecedor.razao_social);
    setCnpj(fornecedor.cnpj);
    setEndereco(fornecedor.endereco);
    setContato(fornecedor.contato);
    setFornecedorEditando(fornecedor); // Entra no modo de edição
  };

  const handleRemove = (fornecedorId) => {
    if (window.confirm("Tem certeza que deseja remover este fornecedor?")) {
      axios.delete(`${baseURL}/${fornecedorId}`)
        .then(() => {
          setFornecedores(fornecedores.filter(f => f.fornecedor_id !== fornecedorId));
          alert("Fornecedor removido com sucesso!");
        })
        .catch(err => {
          console.error("Erro ao remover fornecedor:", err);
          alert("Falha ao remover fornecedor.");
        });
    }
  };

  return (
    <div>
      <h2>📌 Fornecedores</h2>
      
      {/* Formulário de Cadastro/Edição */}
      <h3>{fornecedorEditando ? "✏️ Editar Fornecedor" : "➕ Adicionar Novo Fornecedor"}</h3>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Razão Social:</label>
          <input type="text" value={razaoSocial} onChange={(e) => setRazaoSocial(e.target.value)} maxLength={100} />
        </div>
        <div>
          <label>CNPJ:</label>
          <input type="text" value={cnpj} onChange={(e) => setCnpj(e.target.value.replace(/\D/g, ""))} maxLength={14} />
        </div>
        <div>
          <label>Endereço:</label>
          <input type="text" value={endereco} onChange={(e) => setEndereco(e.target.value)} maxLength={200} />
        </div>
        <div>
          <label>Contato:</label>
          <input type="text" value={contato} onChange={(e) => setContato(e.target.value)} maxLength={50} />
        </div>
        <button type="submit">{fornecedorEditando ? "Atualizar Fornecedor" : "Adicionar Fornecedor"}</button>
      </form>

      <hr style={{ margin: "20px 0" }} />

      {/* Tabela de Consulta */}
      <h3>🔍 Fornecedores Cadastrados</h3>
      <table border="1" cellPadding="5" cellSpacing="0">
        <thead>
          <tr>
            <th>ID</th>
            <th>Razão Social</th>
            <th>CNPJ</th>
            <th>Endereço</th>
            <th>Contato</th>
            <th>Editar</th>
            <th>Remover</th>
          </tr>
        </thead>
        <tbody>
          {fornecedores.length === 0 ? (
            <tr>
              <td colSpan="7" style={{ textAlign: "center" }}>Nenhum fornecedor cadastrado.</td>
            </tr>
          ) : (
            fornecedores.map((f) => (
              <tr key={f.fornecedor_id}>
                <td>{f.fornecedor_id}</td>
                <td>{f.razao_social}</td>
                <td>{f.cnpj}</td>
                <td>{f.endereco}</td>
                <td>{f.contato}</td>
                <td>
                  <button onClick={() => handleEdit(f)}>
                    ✏️
                  </button>
                </td>
                <td>
                  <button onClick={() => handleRemove(f.fornecedor_id)}>
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