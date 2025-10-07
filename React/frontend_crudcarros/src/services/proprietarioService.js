import axios from "axios";

const API_URL = "https://crudcarros.up.railway.app/proprietarios"; // sua API do Spring Boot

export const listarProprietarios = () => {
  return axios.get(API_URL);
};

export const salvarProprietario = (Proprietario) => {
  return axios.post(API_URL, Proprietario);
};

export const atualizarProprietario = (id, Proprietario) => {
  return axios.put(`${API_URL}/${id}`, Proprietario);
};

export const deletarProprietario = (id) => {
  return axios.delete(`${API_URL}/${id}`);
};

export const buscarProprietarioPorId = (id) => axios.get(`${API_URL}/${id}`);