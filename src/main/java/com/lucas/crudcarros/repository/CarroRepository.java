package com.lucas.crudcarros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lucas.crudcarros.model.Carro;

public interface CarroRepository extends JpaRepository <Carro, Integer> {

	public Boolean existsByPlaca(String placa);
	public List<Carro> findByMarca(String marca);
	public List<Carro> findByModelo(String modelo);
	public List<Carro> findByPlaca(String placa);
	public List<Carro> findByProprietarioCpf(String cpf);
	
	@Query
	(
		"SELECT c FROM Carro c " +
		"WHERE (:placa IS NULL OR c.placa = :placa) " +
		"AND (:cpf IS NULL OR c.proprietario.cpf = :cpf%) " +
		"AND (:modelo IS NULL OR c.modelo = :modelo%) " +
		"AND (:marca IS NULL OR c.marca = :marca%) " +
		"AND (:ano IS NULL OR c.ano = :ano) " +
		"AND (:cor IS NULL OR c.cor = :cor%)"
	)
	List<Carro> buscarComFiltros
	(
		@Param("placa") String placa,
		@Param("cpf") String cpf,
		@Param("modelo") String modelo,
		@Param("marca") String marca,
		@Param("ano") Integer ano,
		@Param("cor") String cor
	);
}