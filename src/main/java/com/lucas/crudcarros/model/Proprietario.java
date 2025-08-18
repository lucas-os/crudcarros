package com.lucas.crudcarros.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Proprietario {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cpf;
	
	@OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL, orphanRemoval = true) //cascade = ao salvar um proprietario, os carros vão salvar junto. orphanRemoval = quando o carro for removido da lista, também vai ser removido do banco
	private List<Carro> listaCarros = new ArrayList<>();

	private String telefone;
	private String email;
	private Integer idade;
	private String endereco;
	
	public Proprietario(){
		
	}

	public Proprietario(Integer id, String nome, String cpf, List<Carro> listaCarros, String telefone, String email, Integer idade, String endereco) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.listaCarros = listaCarros;
		this.telefone = telefone;
		this.email = email;
		this.idade = idade;
		this.endereco = endereco;
	}

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

	public List<Carro> getListaCarros() {
		return listaCarros;
	}

	public void setListaCarros(List<Carro> listaCarros) {
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}