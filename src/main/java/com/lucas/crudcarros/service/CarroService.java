package com.lucas.crudcarros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.crudcarros.dto.CarroDTO;
import com.lucas.crudcarros.model.Carro;
import com.lucas.crudcarros.model.Proprietario;
import com.lucas.crudcarros.repository.CarroRepository;
import com.lucas.crudcarros.repository.ProprietarioRepository;

@Service
public class CarroService {

	@Autowired
	CarroRepository carroR;
	
	@Autowired
	ProprietarioRepository proprietarioR;
	
	public CarroDTO salvarCarro(CarroDTO carroDTO) {
		
		validarCarro(carroDTO);

		String cpfP = carroDTO.getCpfProprietario().trim(); // carro.getProprietario().getCpf().trim(); - sem o DTO
	    validarCpfProprietario(cpfP);

	    Proprietario propr = proprietarioR.findByCpf(cpfP);
	    
	    if (propr == null) 
	    {
	        throw new IllegalArgumentException("Não tem nenhum proprietário com esse CPF");
	    }
		
	    Carro carro = paraCarro(carroDTO);
	    carro.setProprietario(propr);
		
		if (carro.getId() == null && carroR.existsByPlaca(carro.getPlaca())) 
		{
	        throw new IllegalArgumentException("Já tem um carro com essa placa");
	    }

	    Carro carroSalvo = carroR.save(carro);
	    CarroDTO carroResposta = paraCarroDTO(carroSalvo);
	    
	    return carroResposta;
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
	
	private void validarCarro(CarroDTO carroDTO) 
	{
	    if (carroDTO.getModelo() == null || carroDTO.getModelo().trim().isEmpty()) 
	    {
	        throw new IllegalArgumentException("Você não colocou o modelo");
	    }
	    if (carroDTO.getMarca() == null || carroDTO.getMarca().trim().isEmpty()) 
	    {
	        throw new IllegalArgumentException("Você não colocou a marca");
	    }
	    if (carroDTO.getPlaca() == null || carroDTO.getPlaca().trim().isEmpty()) 
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
	
	public Carro paraCarro(CarroDTO carroDTO)
	{
		Carro carro = new Carro();
		carro.setPlaca(carroDTO.getPlaca());
		carro.setAno(carroDTO.getAno());
	    carro.setCor(carroDTO.getCor());
	    carro.setMarca(carroDTO.getMarca());
	    carro.setModelo(carroDTO.getModelo());
	    carro.setSituacao(carroDTO.getSituacao());
	    return carro;
	}
	
	public CarroDTO paraCarroDTO(Carro carroSalvo)
	{
		CarroDTO carroResposta = new CarroDTO();
		carroResposta.setPlaca(carroSalvo.getPlaca());
	    carroResposta.setAno(carroSalvo.getAno());
	    carroResposta.setCor(carroSalvo.getCor());
	    carroResposta.setMarca(carroSalvo.getMarca());
	    carroResposta.setModelo(carroSalvo.getModelo());
	    carroResposta.setSituacao(carroSalvo.getSituacao());
	    carroResposta.setCpfProprietario(carroSalvo.getProprietario().getCpf());
	    return carroResposta;
	}
}

