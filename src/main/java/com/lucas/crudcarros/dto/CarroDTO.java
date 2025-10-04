package com.lucas.crudcarros.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CarroDTO {
	
	private Integer id;
	
	@NotBlank
	@Size(min=7, max=7, message="A placa deve ter 7 dígitos")
	private String placa;
	
	@NotBlank
    private String modelo;
	
	@NotBlank
    private String marca;
	
	@Min(1900)
	@Max(2030)
    private Integer ano;
	
	@Pattern(regexp = "^[\\p{L} .'-]+$", message = "Cor inválida — use apenas letras e espaços.")
    private String cor;
    
    @Size(max=2000)
    private String situacao;
    
    @NotBlank
	@Pattern
	(
		regexp="^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", 
		message="CPF deve ser nessa formatação: XXX.XXX.XXX-XX"
	)
    private String cpfProprietario;
    
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
	public String getCpfProprietario() {
		return cpfProprietario;
	}
	public void setCpfProprietario(String cpfProprietario) {
		this.cpfProprietario = cpfProprietario;
	}
}
