package com.lucas.crudcarros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lucas.crudcarros.model.Proprietario;
import com.lucas.crudcarros.repository.ProprietarioRepository;

@Service
public class ProprietarioService {

	@Autowired
	ProprietarioRepository proprietarioR;
	
	
	public void salvarProprietario(Proprietario proprietario) {
		
		if(proprietario.getNome() == null || proprietario.getNome().isEmpty()) 
		{
			throw new IllegalArgumentException("Voce nao colocou o nome");
		}
		if(proprietario.getCpf() == null || proprietario.getCpf().isEmpty()) 
		{
			throw new IllegalArgumentException("Voce nao colocou o cpf");
		}
		
		if(proprietario.getId() == null)
		{
			if(proprietarioR.existsByCpf(proprietario.getCpf()))
			{
				throw new IllegalArgumentException("Ja tem uma pessoa com esse cpf");
			}
			else
				proprietarioR.save(proprietario);
		}
		else
			proprietarioR.save(proprietario);
	}
	
	public List<Proprietario> listarProprietarios(){
		
		List<Proprietario> listaProprietario = proprietarioR.findAll();
		return listaProprietario;
	}
	
	public void removerProprietario(Proprietario proprietario) {
		
		if(proprietario.getId() == null)
		{
			throw new IllegalArgumentException("Proprietario inválido: ID não informado.");
		}
		if(!proprietarioR.existsById(proprietario.getId()))
		{
			throw new IllegalArgumentException("Nenhum proprietario encontrado com o ID informado.");
			
		}
		else
			proprietarioR.deleteById(proprietario.getId());
	}
	
	public List<Proprietario> listarProprietarioPorNome(Proprietario proprietario)
	{
		List<Proprietario> listaProprietarioNome = proprietarioR.findByNome(proprietario.getNome());
		return listaProprietarioNome;
	}
}