package com.lucas.crudcarros.service;

import java.util.List;
import java.util.Optional;

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
	
	public CarroDTO editarCarro(Integer id, CarroDTO dto)
	{
		Optional<Carro> optionalCarro = carroR.findById(id); //Não crio um novo carro, pego o 
		// Verifica se existe
		if (optionalCarro.isEmpty()) 
		{
		    throw new IllegalArgumentException("Nenhum carro encontrado com o ID informado.");
		}
		
		Carro carroExistente = optionalCarro.get();
		
		String cpfP = dto.getCpfProprietario().trim();
		Proprietario propr = proprietarioR.findByCpf(cpfP);
	    
	    if (propr == null) 
	    {
	        throw new IllegalArgumentException("Não tem nenhum proprietário com esse CPF");
	    }
	    
	    carroExistente.setAno(dto.getAno());
	    carroExistente.setCor(dto.getCor());
	    carroExistente.setMarca(dto.getMarca());
	    carroExistente.setModelo(dto.getModelo());
	    carroExistente.setSituacao(dto.getSituacao());
	    carroExistente.setPlaca(dto.getPlaca());
	    carroExistente.setProprietario(propr);
		
		carroR.save(carroExistente);
		dto = paraCarroDTO(carroExistente);
		return dto;
	}
	
	public List<Carro> listarCarroPorMarca(Carro carro)
	{
		List<Carro> listaCarrosMarca = carroR.findByMarca(carro.getMarca());
		return listaCarrosMarca;
	}
	
	public Optional<Carro> buscarPorId(Integer id) {
	    return carroR.findById(id);
	}
	
	public List<Carro> filtrarCarros(String marca, String modelo, String placa, String cpfProprietario)
	{
		if(marca != null && !marca.isEmpty())
		{
			return carroR.findByMarca(marca);
		}
		if(modelo != null && !modelo.isEmpty())
		{
			return carroR.findByModelo(modelo);
		}
		if(placa != null && !placa.isEmpty())
		{
			return carroR.findByPlaca(placa);
		}
		if(cpfProprietario != null && !cpfProprietario.isEmpty())
		{
			return carroR.findByProprietarioCpf(cpfProprietario);
		}
		
		return carroR.findAll();
	}
	
	public List<Carro> listarCarrosComFiltros(String placa, String cpf, String modelo, String marca, Integer ano, String cor)
	{
		String filtroPlaca = placa != null && !placa.isBlank() ? placa : null; //verifica se é null, verifica se não é espaço em branco, se a condição antes do ? for true, retorna cpf, se for false retorna null
		String filtroCpf = cpf != null && !cpf.isBlank() ? cpf : null; 
		String filtroModelo = modelo != null && !modelo.isBlank() ? modelo : null;
		String filtroMarca = marca != null && !marca.isBlank() ? marca : null;
		String filtroCor = cor != null && !cor.isBlank() ? cor : null;
		
		return carroR.buscarComFiltros(filtroPlaca, filtroCpf, filtroModelo, filtroMarca, ano, filtroCor);
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
		carro.setId(carroDTO.getId());
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
		carroResposta.setId(carroSalvo.getId());
		carroResposta.setPlaca(carroSalvo.getPlaca());
	    carroResposta.setAno(carroSalvo.getAno());
	    carroResposta.setCor(carroSalvo.getCor());
	    carroResposta.setMarca(carroSalvo.getMarca());
	    carroResposta.setModelo(carroSalvo.getModelo());
	    carroResposta.setSituacao(carroSalvo.getSituacao());
	    carroResposta.setCpfProprietario(carroSalvo.getProprietario().getCpf());
	    carroResposta.setId(carroSalvo.getId());
	    return carroResposta;
	}
}

