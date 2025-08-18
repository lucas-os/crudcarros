package com.lucas.crudcarros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.crudcarros.model.Carro;
import com.lucas.crudcarros.model.Proprietario;
import com.lucas.crudcarros.repository.CarroRepository;
import com.lucas.crudcarros.repository.ProprietarioRepository;

@Service
public class CarroService {

	@Autowired
	CarroRepository carroR;
	ProprietarioRepository proprietarioR;
	
	public void salvarCarro(Carro carro) {
		
		validarCarro(carro);

	    String cpfP = carro.getProprietario().getCpf().trim();
	    validarCpfProprietario(cpfP);

	    Proprietario propr = proprietarioR.findByCpf(cpfP);
	    
	    if (propr == null) 
	    {
	        throw new IllegalArgumentException("Não tem nenhum proprietário com esse CPF");
	    }
		
		carro.setProprietario(propr);
		
		if (carro.getId() == null && carroR.existsByPlaca(carro.getPlaca())) 
		{
	        throw new IllegalArgumentException("Já tem um carro com essa placa");
	    }

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
	
	public List<Carro> listarCarroPorMarca(Carro carro)
	{
		List<Carro> listaCarrosMarca = carroR.findByMarca(carro.getMarca());
		return listaCarrosMarca;
	}
	
	private void validarCarro(Carro carro) 
	{
	    if (carro.getModelo() == null || carro.getModelo().trim().isEmpty()) 
	    {
	        throw new IllegalArgumentException("Você não colocou o modelo");
	    }
	    if (carro.getMarca() == null || carro.getMarca().trim().isEmpty()) 
	    {
	        throw new IllegalArgumentException("Você não colocou a marca");
	    }
	    if (carro.getPlaca() == null || carro.getPlaca().trim().isEmpty()) 
	    {
	        throw new IllegalArgumentException("Você não colocou a placa");
	    }
	}
	
	private void validarCpfProprietario(String cpf) 
	{
	    if (cpf == null || cpf.isEmpty()) 
	    {
	        throw new IllegalArgumentException("CPF não encontrado");
	    }
	}
}

