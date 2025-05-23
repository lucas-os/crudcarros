package com.lucas.crudcarros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.crudcarros.model.Carro;
import com.lucas.crudcarros.repository.CarroRepository;

@Service
public class CarroService {

	@Autowired
	CarroRepository carroR;
	
	public void salvarCarro(Carro carro) {
		
		if(carro.getModelo() == null || carro.getModelo().isEmpty()) 
		{
			throw new IllegalArgumentException("Voce nao colocou o modelo");
		}
		if(carro.getMarca() == null || carro.getMarca().isEmpty()) 
		{
			throw new IllegalArgumentException("Voce nao colocou a marca");
		}
		if(carro.getPlaca() == null || carro.getPlaca().isEmpty()) 
		{
			throw new IllegalArgumentException("Voce nao colocou a placa");
		}
		
		if(carro.getId() == null)
		{
			if(carroR.existsByPlaca(carro.getPlaca()))
			{
				throw new IllegalArgumentException("Ja tem um carro com essa placa");
			}
			else
				carroR.save(carro);
		}
		else
			carroR.save(carro);
	}
	
	public List<Carro> listarCarros(){
		
		List<Carro> listaCarros = carroR.findAll();
		return listaCarros;
	}
	
	public void removerCarro(Carro carro) {
		
		if(carro.getId() == null)
		{
			throw new IllegalArgumentException("Carro inválido: ID não informado.");
		}
		if(!carroR.existsById(carro.getId()))
		{
			throw new IllegalArgumentException("Nenhum carro encontrado com o ID informado.");
			
		}
		else
			carroR.deleteById(carro.getId());
	}
}

