import React, { useState } from "react";
import { Link } from "react-router-dom";

function SidebarMenu() {
  const [isOpen, setIsOpen] = useState(true);

  return (
    <div className={`sidebar ${isOpen ? "open" : "closed"}`}>
      <button className="toggle-btn" onClick={() => setIsOpen(!isOpen)}>
        ☰
      </button>

      <nav>
      <Link to="/">
          <span className="icon">🏠</span>
          <span className="link-text">Home</span>
        </Link>
        <Link to="/financeiro">
          <span className="icon">💰</span>
          <span className="link-text">Financeiro</span>
        </Link>
        <Link to="/vendas">
          <span className="icon">🛒</span>
          <span className="link-text">Vendas</span>
        </Link>
        <Link to="/estoque">
          <span className="icon">📦</span>
          <span className="link-text">Estoque</span>
        </Link>
        <Link to="/bi">
          <span className="icon">📊</span>
          <span className="link-text">BI</span>
        </Link>
        <Link to="/clientes">
          <span className="icon">👥</span>
          <span className="link-text">Clientes</span>
        </Link>
      </nav>
    </div>
  );
}

export default SidebarMenu;
