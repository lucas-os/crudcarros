import axios from "axios";

const API_URL = "https://crudcarros.up.railway.app/carros"; // sua API do Spring Boot

export const listarCarros = () => {
  return axios.get(API_URL);
};

export const salvarCarro = (carro) => {
  return axios.post(API_URL, carro);
};

export const atualizarCarro = (id, carro) => {
  return axios.put(`${API_URL}/${id}`, carro);
};

export const deletarCarro = (id) => {
  return axios.delete(`${API_URL}/${id}`);
};

export const buscarCarroPorId = (id) => axios.get(`${API_URL}/${id}`);