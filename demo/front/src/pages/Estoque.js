import { useState } from "react";
import Inventario from "./Estoque.filhos/Inventario";
import EntradaSaida from "./Estoque.filhos/EntradaSaida";
import Fornecedores from "./Estoque.filhos/Fornecedores";

function Estoque() {
  const [tela, setTela] = useState(null); // null = mostra menu

  return (
    <div>
      <h1>Estoque</h1>

      {tela === null && (
        <nav className="sub-menu">
          <button onClick={() => setTela("inventario")}>Controle de inventário</button>
          <button onClick={() => setTela("entradaSaida")}>Entrada e saída de produtos</button>
          <button onClick={() => setTela("fornecedores")}>Controle de fornecedores</button>
        </nav>
      )}

      {tela !== null && (
        <div>
          <button onClick={() => setTela(null)}>⬅ Voltar</button>

          {tela === "inventario" && <Inventario />}
          {tela === "entradaSaida" && <EntradaSaida />}
          {tela === "fornecedores" && <Fornecedores />}
        </div>
      )}
    </div>
  );
}

export default Estoque;
