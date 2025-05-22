package com.lucas.crudcarros.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Carro {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique = true, nullable = false)
	private String placa;
	
	@Column(unique = true, nullable = false)
	private String modelo;
	
	@Column(unique = true, nullable = false)
	private String marca;
	
	private Integer ano;
	private String cor;
	private String situacao;
	
	public Carro(){
		
	}

	public Carro(Integer id, String placa, String modelo, String marca, Integer ano, String cor, String situacao) {
		this.id = id;
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.ano = ano;
		this.cor = cor;
		this.situacao = situacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}