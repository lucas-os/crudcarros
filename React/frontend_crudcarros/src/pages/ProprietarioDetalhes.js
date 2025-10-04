import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { buscarProprietarioPorId, deletarProprietario } from "../services/proprietarioService";

function ProprietarioDetalhes() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [propr, setProprietario] = useState(null);

  useEffect(() => {
    buscarProprietarioPorId(id)
      .then((response) => {
        setProprietario(response.data);
      })
      .catch(() => {
        alert("Erro ao carregar os detalhes do proprietário.");
        navigate("/proprietarios/lista");
      });
  }, [id, navigate]);

  const handleEditar = () => {
    navigate(`/proprietarios/editar/${id}`);
  };

  const handleExcluir = () => {
    if (window.confirm("Tem certeza que deseja excluir este proprietário?")) {
      deletarProprietario(id)
        .then(() => {
          alert("Proprietário excluído com sucesso!");
          navigate("/proprietarios/lista");
        })
        .catch(() => alert("Erro ao excluir proprietário."));
    }
  };

  if (!propr) {
    return (
      <div className="min-h-screen flex items-center justify-center text-xl text-gray-600">
        Carregando detalhes...
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-100 p-6 font-sans">
      <div className="max-w-3xl mx-auto bg-white shadow-lg rounded-lg p-6 border-t-8 border-yellow-500">
        <h1 className="text-3xl font-bold mb-6 text-center text-red-700">
          👤 Detalhes do Proprietário
        </h1>

        <div className="space-y-4 text-gray-700">
          <p><strong>CPF:</strong> {propr.cpf}</p>
          <p><strong>Nome:</strong> {propr.nome}</p>
          <p><strong>Telefone:</strong> {propr.telefone}</p>
          <p><strong>Email:</strong> {propr.email}</p>
          <p><strong>Idade:</strong> {propr.idade}</p>
          <p><strong>Endereço:</strong> {propr.endereco}</p>

          <p className="whitespace-pre-line bg-gray-100 p-3 rounded">
            <strong className="text-gray-800">Carros:</strong><br />
            {propr.listaCarros && propr.listaCarros.length > 0 ? (
              propr.listaCarros.map((carro) => (
                <div key={carro.id}>
                  {carro.placa} - {carro.modelo}
                </div>
              ))
            ) : (
              <span>Nenhum carro cadastrado</span>
            )}
          </p>

        </div>

        <div className="mt-6 flex justify-between">
          <button
            onClick={() => navigate("/proprietarios/lista")}
            className="bg-gray-400 text-white px-4 py-2 rounded hover:bg-gray-500 transition"
          >
            Voltar
          </button>

          <div className="flex gap-3">
            <button
              onClick={handleEditar}
              className="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600 transition"
            >
              Editar
            </button>
            <button
              onClick={handleExcluir}
              className="bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700 transition"
            >
              Excluir
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ProprietarioDetalhes;