import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { buscarCarroPorId, deletarCarro } from "../services/carroService";

function CarroDetalhes() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [carro, setCarro] = useState(null);

  useEffect(() => {
    buscarCarroPorId(id)
      .then((response) => {
        setCarro(response.data);
      })
      .catch(() => {
        alert("Erro ao carregar os detalhes do carro.");
        navigate("/carros/lista");
      });
  }, [id, navigate]);

  const handleEditar = () => {
    navigate(`/carros/editar/${id}`);
  };

  const handleExcluir = () => {
    if (window.confirm("Tem certeza que deseja excluir este carro?")) {
      deletarCarro(id)
        .then(() => {
          alert("Carro excluído com sucesso!");
          navigate("/carros/lista");
        })
        .catch(() => alert("Erro ao excluir carro."));
    }
  };

  if (!carro) {
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
          🚗 Detalhes do Carro
        </h1>

        <div className="space-y-4 text-gray-700">
          <p><strong>Placa:</strong> {carro.placa}</p>
          <p><strong>Marca:</strong> {carro.marca}</p>
          <p><strong>Modelo:</strong> {carro.modelo}</p>
          <p><strong>Ano:</strong> {carro.ano}</p>
          <p><strong>Cor:</strong> {carro.cor}</p>
          <p className="whitespace-pre-line bg-gray-100 p-3 rounded">
            <strong className="text-gray-800">Situação:</strong><br />
            {carro.situacao}
          </p>

        </div>

        <div className="mt-6 flex justify-between">
          <button
            onClick={() => navigate("/carros/lista")}
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

export default CarroDetalhes;