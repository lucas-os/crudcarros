package com.lucas.crudcarros.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Carro {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique = true, nullable = false)
	private String placa;
	
	@Column(nullable = false)
	private String modelo;
	
	@Column(nullable = false)
	private String marca;
	
	private Integer ano;
	private String cor;
	private String situacao;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY) // toda vez que buscar um carro, evita carregar o proprietario junto
	@JoinColumn(name = "proprietario_id") // nome da coluna no banco
	private Proprietario proprietario;
	
	public Carro(){
		
	}
	
	public Carro(Integer id, String placa, String modelo, String marca, Integer ano, String cor, String situacao, Proprietario proprietario) {
		this.id = id;
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.ano = ano;
		this.cor = cor;
		this.situacao = situacao;
		this.proprietario = proprietario;
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

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}
}