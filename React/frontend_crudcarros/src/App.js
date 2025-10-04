import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import CarroPage from "./pages/CarroPage";
import ProprietarioPage from "./pages/ProprietarioPage";
import ListaCarros from "./components/ListaCarros";
import EditarCarro from "./components/EditarCarro";
import ListaProprietarios from "./components/ListaProprietarios";
import EditarProprietario from "./components/EditarProprietario";
import CarroDetalhes from "./pages/CarroDetalhes";
import ProprietarioDetalhes from "./pages/ProprietarioDetalhes";

function HomePage() {
  return (
    <div className="min-h-screen bg-gray-100 flex flex-col">
      {/* Navbar */}
      <header className="bg-gray-900 text-white p-4 shadow-md">
        <h1 className="text-2xl font-bold">Oficina System</h1>
      </header>

      {/* Conteúdo */}
      <main className="flex-1 flex flex-col items-center justify-center p-6">
        <h2 className="text-3xl font-semibold mb-8 text-gray-800">
          Bem-vindo ao Sistema de Oficina 🚗🔧
        </h2>

        {/* Opções principais */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-8 max-w-4xl w-full">
          {/* Card Carros */}
          <Link
            to="/carros"
            className="bg-white shadow-lg shadow-yellow-600/20 rounded-xl p-8 flex flex-col items-center hover:shadow-2xl hover:scale-105 transition hover:shadow-yellow-600/40"
          >
            <span className="text-5xl mb-4">🚗</span>
            <h3 className="text-xl font-bold text-red-800 mb-2">Gerenciar Carros</h3>
            <p className="text-yellow-600 text-center">
              Cadastre, edite e visualize os carros cadastrados.
            </p>
          </Link>

          {/* Card Proprietários */}
          <Link
            to="/proprietarios"
            className="bg-white shadow-lg shadow-yellow-600/20 rounded-xl p-8 flex flex-col items-center hover:shadow-2xl hover:scale-105 transition hover:shadow-yellow-600/40"
          >
            <span className="text-5xl mb-4">👤</span>
            <h3 className="text-xl font-bold text-red-800 mb-2">Gerenciar Proprietários</h3>
            <p className="text-yellow-600 text-center">
              Cadastre e acompanhe os proprietários dos veículos.
            </p>
          </Link>
        </div>
      </main>

      {/* Rodapé */}
      <footer className="bg-gray-900 text-white text-center p-4">
        <p className="text-sm">&copy; 2025 Oficina System - Desenvolvido por Lucas Oliveira Silvério</p>
      </footer>
    </div>
  );
}

function App() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/carros" element={<CarroPage />} />
      <Route path="/carros/lista" element={<ListaCarros />} />
      <Route path="/carros/editar/:id" element={<EditarCarro />} />
      <Route path="/proprietarios" element={<ProprietarioPage />} />
      <Route path="/proprietarios/lista" element={<ListaProprietarios />} />
      <Route path="/proprietarios/editar/:id" element={<EditarProprietario />} />
      <Route path="/carros/detalhes/:id" element={<CarroDetalhes />} />
      <Route path="/proprietarios/detalhes/:id" element={<ProprietarioDetalhes />} />
    </Routes>

  );
}

export default App;
