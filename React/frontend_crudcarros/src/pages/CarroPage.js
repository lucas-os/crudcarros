import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { IMaskInput } from "react-imask";

function CarroPage() {
  const [form, setForm] = useState({
    placa: "",
    marca: "",
    modelo: "",
    cor: "",
    ano: "",
    situacao: "",
    cpfProprietario: ""
  });
  const [erros, setErros] = useState({});
  const API_URL = "https://crudcarros.up.railway.app/carros";
  const navigate = useNavigate();

  // Atualiza os inputs
  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  // Envia o formulário
  const handleSubmit = async (e) => {
    e.preventDefault();
    setErros({}); // limpa erros antes de enviar

    try {
      const response = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });

      if (!response.ok) {
        const data = await response.json();
        if (data.erro || data.error) {
          alert(data.erro || data.error);
        } else {
          setErros(data);
        }
        return;
      }

      // Sucesso
      alert("Carro cadastrado com sucesso!");
      setForm({
        placa: "",
        marca: "",
        modelo: "",
        cor: "",
        ano: "",
        situacao: "",
        cpfProprietario: "",
      });

    } catch (error) {
      console.error("Erro ao cadastrar carro:", error);
      alert("Erro de conexão com o servidor.");
    }
  };

     return (
    <div className="min-h-screen bg-gray-100 p-6 font-sans">
      <div className="max-w-4xl mx-auto bg-white shadow-lg rounded-lg p-6 border-t-8 border-red-600">
        <h1 className="text-3xl font-bold mb-4 text-center text-red-700">
          🚗 Cadastro de Carros
        </h1>

        {/* Botão Voltar */}
        <button
          onClick={() => navigate("/")}
          className="bg-red-500 text-white px-4 py-2 rounded hover:bg-yellow-600 transition duration-200 mb-4 inline-block"
        >
          Voltar
        </button>

        <form onSubmit={handleSubmit} className="mb-4 space-y-4">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">

            {/* CPF do Proprietário */}
            <div>
              <IMaskInput
                mask="000.000.000-00"
                value={form.cpfProprietario}
                onAccept={(value) => setForm({ ...form, cpfProprietario: value })}
                placeholder="CPF do Proprietário"
                className={`border rounded px-3 py-2 w-full focus:outline-none focus:ring-2 ${
                  erros.cpfProprietario ? "border-red-600 ring-red-500" : "focus:ring-yellow-500"
                }`}
              />
              <span className="text-red-600 text-sm block mt-1 h-4">{erros.cpfProprietario}</span>
            </div>

            {/* Placa */}
            <div>
              <input
                type="text"
                name="placa"
                placeholder="Placa"
                value={form.placa}
                onChange={handleChange}
                maxLength={7}
                className={`border rounded px-3 py-2 w-full focus:outline-none focus:ring-2 ${
                  erros.placa ? "border-red-600 ring-red-500" : "focus:ring-yellow-500"
                }`}
              />
              <span className="text-red-600 text-sm block mt-1 h-4">{erros.placa}</span>
            </div>

            {/* Marca */}
            <div>
              <input
                type="text"
                name="marca"
                placeholder="Marca"
                value={form.marca}
                onChange={handleChange}
                className={`border rounded px-3 py-2 w-full focus:outline-none focus:ring-2 ${
                  erros.marca ? "border-red-600 ring-red-500" : "focus:ring-yellow-500"
                }`}
              />
              <span className="text-red-600 text-sm block mt-1 h-4">{erros.marca}</span>
            </div>

            {/* Modelo */}
            <div>
              <input
                type="text"
                name="modelo"
                placeholder="Modelo"
                value={form.modelo}
                onChange={handleChange}
                className={`border rounded px-3 py-2 w-full focus:outline-none focus:ring-2 ${
                  erros.modelo ? "border-red-600 ring-red-500" : "focus:ring-yellow-500"
                }`}
              />
              <span className="text-red-600 text-sm block mt-1 h-4">{erros.modelo}</span>
            </div>

            {/* Ano */}
            <div>
              <input
                type="number"
                name="ano"
                placeholder="Ano"
                value={form.ano}
                onChange={handleChange}
                className={`border rounded px-3 py-2 w-full focus:outline-none focus:ring-2 ${
                  erros.ano ? "border-red-600 ring-red-500" : "focus:ring-yellow-500"
                }`}
              />
              <span className="text-red-600 text-sm block mt-1 h-4">{erros.ano}</span>
            </div>

            {/* Cor */}
            <div>
              <input
                type="text"
                name="cor"
                placeholder="Cor"
                value={form.cor}
                onChange={handleChange}
                className={`border rounded px-3 py-2 w-full focus:outline-none focus:ring-2 ${
                  erros.cor ? "border-red-600 ring-red-500" : "focus:ring-yellow-500"
                }`}
              />
              <span className="text-red-600 text-sm block mt-1 h-4">{erros.cor}</span>
            </div>

            {/* Situação */}
            <div className="md:col-span-2">
              <textarea
                name="situacao"
                placeholder="Situação"
                value={form.situacao}
                onChange={handleChange}
                rows="3"
                className={`border rounded px-3 py-2 w-full focus:outline-none focus:ring-2 ${
                  erros.situacao ? "border-red-600 ring-red-500" : "focus:ring-yellow-500"
                }`}
              />
              <span className="text-red-600 text-sm block mt-1 h-4">{erros.situacao}</span>
            </div>

          </div>

          <button
            type="submit"
            className="bg-green-600 text-white px-6 py-2 rounded shadow hover:bg-green-700 transition duration-200"
          >
            Cadastrar
          </button>
        </form>

        <button
          onClick={() => navigate("/carros/lista")}
          className="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600 transition duration-200"
        >
          Listar Carros
        </button>
      </div>
    </div>
  );

}

export default CarroPage;