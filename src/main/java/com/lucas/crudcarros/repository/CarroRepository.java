package com.lucas.crudcarros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.crudcarros.model.Carro;

public interface CarroRepository extends JpaRepository <Carro, Integer> {

}
