import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { useNavigate } from "react-router-dom"; //navegar entre paginas
import { IMask, IMaskInput } from "react-imask";

function ListaProprietarios() {
  const [proprietarios, setProprietarios] = useState([]);
  const [filtroCpf, setFiltroCpf] = useState(""); // filtro da barra de pesquisa
  const API_URL = "https://crudcarros.up.railway.app/proprietarios";
  const navigate = useNavigate();
  const [erro, setErro] = useState("");

  // Carrega os proprietarios ao iniciar a página
  useEffect(() => {
    fetchProprietarios();
  }, []);

  // Busca todos os proprietarios ou filtra por nome
  //useEffect(() => {
    /*
  const fetchProprietarios = async () => {
    try {
      let url = API_URL;
      if (filtroCpf.trim() !== "") {
        url = `${API_URL}/cpf?cpf=${filtroCpf}`; // backend busca pelo nome do proprietario
      }
      const response = await axios.get(url);
      setProprietarios(response.data);
    } catch (error) {
      console.error("Erro ao buscar proprietarios:", error);
    }
  };*/

  const fetchProprietarios = async () => {
  try {
    setErro(""); // limpa erro anterior
    let url = API_URL;
    if (filtroCpf.trim() !== "") {
      url = `${API_URL}/cpf?cpf=${filtroCpf}`;
    }
    const response = await axios.get(url);

    if (filtroCpf.trim() !== "") {
      setProprietarios([response.data]);
    } else {
      setProprietarios(response.data);
    }
  } catch (error) {
    // Aqui pegamos a mensagem que vem do backend
    if (error.response && error.response.data && error.response.data.error) {
      setErro(error.response.data.error);
      setProprietarios([]); // limpa a tabela se não encontrou
    } else {
      console.error("Erro ao buscar proprietarios:", error);
    }
  }
};



  // Atualiza o input da barra de pesquisa
  const handleChange = (e) => setFiltroCpf(e.target.value);

  // Pesquisa proprietarios ao clicar no botão ou pressionar Enter
  const handleBuscar = (e) => {
    e.preventDefault();
    fetchProprietarios();
  };

  return (
    <div className="min-h-screen bg-gray-100 p-6 font-sans">
      <div className="max-w-6xl mx-auto bg-white shadow-lg rounded-lg p-6 border-2 border-red-600">
        
        <h1 className="text-3xl font-bold mb-4 text-center text-gray-800">
          👨‍💼 Lista de Proprietários
        </h1>

        {/* Botão voltar */}
        <Link
          to="/proprietarios"
          className="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600 transition duration-200 mb-4 inline-block"
        >
          Voltar
        </Link>

        {/* Barra de pesquisa */}
        <form onSubmit={handleBuscar} className="mb-4 flex gap-2">
          <IMaskInput
            mask="000.000.000-00"
            placeholder="Pesquisar por CPF"
            value={filtroCpf}
            onAccept={(value) => setFiltroCpf(value)} 
            className="flex-1 border rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-yellow-500"
          />
          <button
            type="submit"
            className="bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700 transition duration-200"
          >
            Buscar
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
                <th className="px-4 py-2 text-left">CPF</th>
                <th className="px-4 py-2 text-left">Nome</th>
                <th className="px-4 py-2 text-left">Telefone</th>
                <th className="px-4 py-2 text-left">Email</th>
                <th className="px-4 py-2 text-left">Idade</th>
                <th className="px-4 py-2 text-left">Endereço</th>
              </tr>
            </thead>
            <tbody>
            {proprietarios.map((proprietario) => (
                <tr key={proprietario.id} 
                  onClick={() => navigate(`/proprietarios/detalhes/${proprietario.id}`)} 
                  className="cursor-pointer hover:bg-yellow-100 transition duration-200 border-b"
                >
                    <td className="px-4 py-2 border-r border-gray-300">{proprietario.cpf}</td>
                    <td className="px-4 py-2">{proprietario.nome}</td>
                    <td className="px-4 py-2">{proprietario.telefone}</td>
                    <td className="px-4 py-2">{proprietario.email}</td>
                    <td className="px-4 py-2">{proprietario.idade}</td>
                    <td className="px-4 py-2">{proprietario.endereco}</td>
                </tr>        
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default ListaProprietarios;