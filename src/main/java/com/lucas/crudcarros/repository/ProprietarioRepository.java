package com.lucas.crudcarros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lucas.crudcarros.model.Proprietario;

public interface ProprietarioRepository extends JpaRepository <Proprietario, Integer> {

	public Boolean existsByCpf(String cpf);
	public List<Proprietario> findByNome(String nome);
	public Proprietario findByCpf(String cpf);
}
