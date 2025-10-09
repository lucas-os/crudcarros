import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { IMaskInput } from "react-imask";

function ProprietarioPage() {
  const [form, setForm] = useState({
    nome: "",
    cpf: "",
    telefone: "",
    email: "",
    idade: "",
    endereco: ""
  });
  const [erros, setErros] = useState({});
  const API_URL = "https://crudcarros.up.railway.app/proprietarios";
  const navigate = useNavigate();

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

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

      alert("Proprietário cadastrado com sucesso!");
      setForm({
        nome: "",
        cpf: "",
        telefone: "",
        email: "",
        idade: "",
        endereco: ""
      });

    } catch (error) {
      console.error("Erro ao cadastrar proprietario:", error);
      alert("Erro de conexão com o servidor.");
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 p-6 font-sans">
      <div className="max-w-4xl mx-auto bg-white shadow-lg rounded-lg p-6 border-t-8 border-red-600">
        <h1 className="text-3xl font-bold mb-4 text-center text-red-700">
          👤 Cadastro de Proprietários
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

            {/* CPF */}
            <div>
              <IMaskInput
                mask="000.000.000-00"
                value={form.cpf}
                onAccept={(value) => setForm({ ...form, cpf: value })}
                placeholder="CPF"
                className={`border rounded px-3 py-2 w-full focus:outline-none focus:ring-2 ${
                  erros.cpf ? "border-red-600 ring-red-500" : "focus:ring-yellow-500"
                }`}
              />
              <span className="text-red-600 text-sm block mt-1 h-4">{erros.cpf}</span>
            </div>

            {/* Nome */}
            <div>
              <input
                type="text"
                name="nome"
                placeholder="Nome"
                value={form.nome}
                onChange={handleChange}
                className={`border rounded px-3 py-2 w-full focus:outline-none focus:ring-2 ${
                  erros.nome ? "border-red-600 ring-red-500" : "focus:ring-yellow-500"
                }`}
              />
              <span className="text-red-600 text-sm block mt-1 h-4">{erros.nome}</span>
            </div>

            {/* Telefone */}
            <div>
              <IMaskInput
                mask="(00) 00000-0000"
                value={form.telefone}
                onAccept={(value) => setForm({ ...form, telefone: value })}
                placeholder="Telefone"
                className={`border rounded px-3 py-2 w-full focus:outline-none focus:ring-2 ${
                  erros.telefone ? "border-red-600 ring-red-500" : "focus:ring-yellow-500"
                }`}
              />
              <span className="text-red-600 text-sm block mt-1 h-4">{erros.telefone}</span>
            </div>

            {/* Email */}
            <div>
              <input
                type="text"
                name="email"
                placeholder="Email"
                value={form.email}
                onChange={handleChange}
                className={`border rounded px-3 py-2 w-full focus:outline-none focus:ring-2 ${
                  erros.email ? "border-red-600 ring-red-500" : "focus:ring-yellow-500"
                }`}
              />
              <span className="text-red-600 text-sm block mt-1 h-4">{erros.email}</span>
            </div>

            {/* Idade */}
            <div>
              <input
                type="number"
                name="idade"
                placeholder="Idade"
                value={form.idade}
                onChange={handleChange}
                className={`border rounded px-3 py-2 w-full focus:outline-none focus:ring-2 ${
                  erros.idade ? "border-red-600 ring-red-500" : "focus:ring-yellow-500"
                }`}
              />
              <span className="text-red-600 text-sm block mt-1 h-4">{erros.idade}</span>
            </div>

            {/* Endereço */}
            <div>
              <input
                type="text"
                name="endereco"
                placeholder="Endereço"
                value={form.endereco}
                onChange={handleChange}
                className={`border rounded px-3 py-2 w-full focus:outline-none focus:ring-2 ${
                  erros.endereco ? "border-red-600 ring-red-500" : "focus:ring-yellow-500"
                }`}
              />
              <span className="text-red-600 text-sm block mt-1 h-4">{erros.endereco}</span>
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
          onClick={() => navigate("/proprietarios/lista")}
          className="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600 transition duration-200"
        >
          Listar Proprietários
        </button>
      </div>
    </div>
  );

}

export default ProprietarioPage;