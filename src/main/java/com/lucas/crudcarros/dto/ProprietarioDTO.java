package com.lucas.crudcarros.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProprietarioDTO {
	
	private Integer id;
	
	@NotBlank
	@Pattern(regexp = "^[\\p{L} .'-]+$", message = "Nome inválido — use apenas letras e espaços.")
	private String nome;
	
	@NotBlank
	@Pattern
	(
		regexp="^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", 
		message="CPF deve ser nessa formatação: XXX.XXX.XXX-XX"
	)
    private String cpf;
	
	@NotBlank
	@Pattern
	(
		regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$",
		message = "Digite nesse formato: (XX) XXXXX-XXXX"
	)
    private String telefone;
    
    @Email(message="Email inválido")
    private String email;
    
    @Min(value = 0, message = "Idade não pode ser negativa")
    @Max(value = 120, message = "Idade deve ser no máximo 120 anos")
    private Integer idade;
    
    @NotBlank
    @Size(max=200)
    private String endereco;
    
    private List<ResumoCarroDTO> listaCarros = new ArrayList<>();
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<ResumoCarroDTO> getListaCarros() {
		return listaCarros;
	}
	public void setListaCarros(List<ResumoCarroDTO> listaCarros) {
		this.listaCarros = listaCarros;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
