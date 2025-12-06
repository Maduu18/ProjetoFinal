import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import SidebarMenu from "./components/SidebarMenu";
import "./App.css";

// Páginas
import Home from "./pages/Home";
import Financeiro from "./pages/Financeiro";
import Vendas from "./pages/Vendas";
import Estoque from "./pages/Estoque";
import BI from "./pages/BI";
import Clientes from "./pages/Clientes";

function App() {
  return (
    <Router>
      <div className="app-container">
        <SidebarMenu />
        <main className="main-content">
          <Routes>
            {/* Home como padrão */}
            <Route path="/" element={<Home />} />
            <Route path="/Financeiro/*" element={<Financeiro />} />
            <Route path="/Vendas/*" element={<Vendas />} />
            <Route path="/Estoque/*" element={<Estoque />} />
            <Route path="/Bi/*" element={<BI />} />
            <Route path="/Clientes" element={<Clientes />} />
            <Route path="/Financeiro.filhos/*" element={<Financeiro />} />

            {/* Qualquer rota desconhecida redireciona para Home */}
            <Route path="*" element={<Home />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
