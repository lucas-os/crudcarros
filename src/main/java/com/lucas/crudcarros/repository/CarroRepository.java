package com.lucas.crudcarros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.crudcarros.model.Carro;

public interface CarroRepository extends JpaRepository <Carro, Integer> {

	public Boolean existsByPlaca(String placa);
	public List<Carro> findByMarca(String marca);
}
