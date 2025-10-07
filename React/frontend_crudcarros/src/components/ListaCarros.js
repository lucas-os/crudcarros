import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import { IMask, IMaskInput } from "react-imask";

function ListaCarros() {
  const [carros, setCarros] = useState([]);
  const [filtros, setFiltros] = useState({
    cpfProprietario: "",
    placa: "",
    marca: "",
    modelo: "",
    ano: "",
    cor: "",
  });
  const [erro, setErro] = useState(""); // mensagem de erro
  const navigate = useNavigate();
  const API_URL = "https://crudcarros.up.railway.app/carros";

  // Carrega todos os carros ao iniciar
  useEffect(() => {
    carregarCarros();
  }, []);

  const carregarCarros = async (param = "") => {
    try {
      setErro(""); // limpa erro anterior
      let url = API_URL;
      if (param) {
        url = `${API_URL}/filtros?${param}`;
      }
      const response = await axios.get(url);

      if (Array.isArray(response.data) && response.data.length === 0) {
        setErro("Nenhum carro encontrado com os filtros aplicados.");
        setCarros([]);
      } else {
        setCarros(response.data);
      }
    } catch (error) {
      if (error.response && error.response.data && error.response.data.error) {
        setErro(error.response.data.error);
      } else {
        console.error("Erro ao carregar carros:", error);
        setErro("Erro ao buscar carros. Tente novamente.");
      }
      setCarros([]);
    }
  };

  // Atualiza os inputs de filtro
  const handleChange = (e) => {
    setFiltros({ ...filtros, [e.target.name]: e.target.value });
  };

  // Aplica os filtros
  const aplicarFiltro = () => {
    const params = Object.entries(filtros)
      .filter(([key, value]) => value.trim() !== "")
      .map(([key, value]) => {
        if (key === "ano") return `${key}=${Number(value)}`;
        return `${key}=${encodeURIComponent(value)}`;
      });

    const paramString = params.join("&");
    carregarCarros(paramString);
  };

  return (
    <div className="min-h-screen bg-gray-100 p-6 font-sans">
      <div className="max-w-6xl mx-auto bg-white shadow-lg rounded-lg p-6 border-2 border-red-600">
        <h1 className="text-3xl font-bold mb-4 text-center text-gray-800">
          🚗 Lista de Carros
        </h1>

        <Link
          to="/carros"
          className="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600 transition duration-200 mb-4 inline-block"
        >
          Voltar
        </Link>

        {/* Filtros */}
        <form
          onSubmit={(e) => {
            e.preventDefault(); // previne reload
            aplicarFiltro();    // filtra ao apertar Enter
          }}
          className="flex flex-wrap gap-2 mb-4"
        >
          {Object.keys(filtros).map((campo) =>
            campo === "cpfProprietario" ? (
              <IMaskInput
                key={campo}
                mask="000.000.000-00"
                name={campo}
                placeholder="CPF Proprietário"
                value={filtros[campo]}
                onAccept={(value) => setFiltros({ ...filtros, [campo]: value })}
                className="border rounded px-3 py-2 w-40 focus:outline-none focus:ring-2 focus:ring-yellow-500"
              />
            ) : campo === "placa" ? (
              <input
                key={campo}
                type="text"
                name={campo}
                placeholder="Placa"
                maxLength={7} // limita 7 caracteres
                value={filtros[campo]}
                onChange={handleChange}
                className="border rounded px-3 py-2 w-40 focus:outline-none focus:ring-2 focus:ring-yellow-500"
              />
            ) : (
              <input
                key={campo}
                type={campo === "ano" ? "number" : "text"}
                name={campo}
                placeholder={campo.charAt(0).toUpperCase() + campo.slice(1)}
                value={filtros[campo]}
                onChange={handleChange}
                className="border rounded px-3 py-2 w-40 focus:outline-none focus:ring-2 focus:ring-yellow-500"
              />
            )
          )}

          <button
            type="submit"
            className="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600 transition duration-200"
          >
            Filtrar
          </button>
        </form>


        {/* Mensagem de erro */}
        {erro && (
          <div className="mb-4 p-2 bg-red-100 text-red-700 rounded">
            {erro}
          </div>
        )}

        {/* Tabela */}
        <div className="overflow-x-auto">
          <table className="min-w-full border divide-y divide-gray-300">
            <thead className="bg-red-600 text-white">
              <tr>
                <th className="px-4 py-2 text-left">CPF Proprietário</th>
                <th className="px-4 py-2 text-left">Placa</th>
                <th className="px-4 py-2 text-left">Marca</th>
                <th className="px-4 py-2 text-left">Modelo</th>
                <th className="px-4 py-2 text-left">Ano</th>
                <th className="px-4 py-2 text-left">Cor</th>
                <th className="px-4 py-2 text-left">Situação</th>
              </tr>
            </thead>
            <tbody>
              {carros.map((carro) => (
                <tr
                  key={carro.id}
                  onClick={() => navigate(`/carros/detalhes/${carro.id}`)}
                  className="cursor-pointer hover:bg-yellow-100 transition duration-200 border-b"
                >
                  <td className="px-4 py-2 border-r border-gray-300">{carro.cpfProprietario}</td>
                  <td className="px-4 py-2">{carro.placa}</td>
                  <td className="px-4 py-2">{carro.marca}</td>
                  <td className="px-4 py-2">{carro.modelo}</td>
                  <td className="px-4 py-2">{carro.ano}</td>
                  <td className="px-4 py-2">{carro.cor}</td>
                  <td className="px-4 py-2 max-w-[200px] truncate">{carro.situacao}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default ListaCarros;