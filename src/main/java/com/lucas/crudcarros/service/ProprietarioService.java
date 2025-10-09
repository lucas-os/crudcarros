package com.lucas.crudcarros.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lucas.crudcarros.dto.ProprietarioDTO;
import com.lucas.crudcarros.dto.ResumoCarroDTO;
import com.lucas.crudcarros.exception.ValidationException;
import com.lucas.crudcarros.model.Carro;
import com.lucas.crudcarros.model.Proprietario;
import com.lucas.crudcarros.repository.CarroRepository;
import com.lucas.crudcarros.repository.ProprietarioRepository;

@Service
public class ProprietarioService {

	@Autowired
	ProprietarioRepository proprietarioR;
	
	@Autowired
	CarroRepository carroR;
	
	public ProprietarioDTO salvarProprietario(ProprietarioDTO proprDTO) {
		
		validarProprietario(proprDTO);
		Proprietario propr = paraProprietario(proprDTO);
		
		if(proprDTO.getId() == null && proprietarioR.existsByCpf(proprDTO.getCpf()))
		{
			throw new ValidationException("cpf", "Já existe uma pessoa cadastrada com esse CPF");	
		}
		
		Proprietario proprSalvo = proprietarioR.save(propr);
		ProprietarioDTO proprResposta = paraProprietarioDTO(proprSalvo);
		return proprResposta;
	}
	
	public List<ProprietarioDTO> listarProprietarios(){
		
		List<ProprietarioDTO> listaDTO = new ArrayList<>();
		
		for(Proprietario p : proprietarioR.findAll())
		{
			ProprietarioDTO proprDTO = paraProprietarioDTO(p);
			listaDTO.add(proprDTO);
		}

		return listaDTO;
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
	
	public ProprietarioDTO editarProprietario(Integer id, ProprietarioDTO dto)
	{
		Optional<Proprietario> optionalProprietario = proprietarioR.findById(id); //Não crio um novo carro, pego o que ja existe
		// Verifica se existe
		if (optionalProprietario.isEmpty()) 
		{
		    throw new IllegalArgumentException("Nenhum proprietário encontrado com o ID informado.");
		}
		
		Proprietario proprExistente = optionalProprietario.get();
		
		String cpf = dto.getCpf().trim();
		Proprietario propr = proprietarioR.findByCpf(cpf);
	    
	    if (propr != null && !propr.getId().equals(id)) 
	    {
	        throw new DataIntegrityViolationException("Já existe um proprietário com esse CPF");
	    }
	    
	    proprExistente.setCpf(dto.getCpf());
	    proprExistente.setNome(dto.getNome());
	    proprExistente.setTelefone(dto.getTelefone());
	    proprExistente.setEmail(dto.getEmail());
	    proprExistente.setIdade(dto.getIdade());
	    proprExistente.setEndereco(dto.getEndereco());
		
		proprietarioR.save(proprExistente);
		dto = paraProprietarioDTO(proprExistente);
		return dto;
	}
	
	public ProprietarioDTO listarProprietarioPorCpf(String cpf)
	{
		Proprietario proprietario = proprietarioR.findByCpf(cpf);
	    if (proprietario == null) {
	        throw new IllegalArgumentException("Nenhum proprietário encontrado com esse CPF");
	    }
	    return paraProprietarioDTO(proprietario);
	}
	
	public List<ProprietarioDTO> listarProprietarioPorNome(String nome)
	{
		List<Proprietario> listaProprietarioNome = proprietarioR.findByNome(nome);
		List<ProprietarioDTO> listaDTO = new ArrayList<>();
		
		for(Proprietario p : listaProprietarioNome)
		{
			ProprietarioDTO proprDTO = paraProprietarioDTO(p);
			listaDTO.add(proprDTO);
		}
		
		return listaDTO;
	}
	
	//public List<ProprietarioDTO> listarCarrosProprietario(String )
	
	public Optional<Proprietario> buscarPorId(Integer id) {
	    return proprietarioR.findById(id);
	}
	
	private void validarProprietario(ProprietarioDTO proprDTO) 
	{
		if(proprDTO.getNome() == null || proprDTO.getNome().isEmpty()) 
		{
			throw new IllegalArgumentException("Voce nao colocou o nome");
		}
		if(proprDTO.getCpf() == null || proprDTO.getCpf().isEmpty()) 
		{
			throw new IllegalArgumentException("Voce nao colocou o cpf");
		}
	}
	
	public Proprietario paraProprietario(ProprietarioDTO proprDTO)
	{
		Proprietario propr = new Proprietario();
		propr.setId(proprDTO.getId());
		propr.setCpf(proprDTO.getCpf());
		propr.setEmail(proprDTO.getEmail());
		propr.setEndereco(proprDTO.getEndereco());
		propr.setIdade(proprDTO.getIdade());
		propr.setNome(proprDTO.getNome());
		propr.setTelefone(proprDTO.getTelefone());
	    return propr;
	}
	
	public ProprietarioDTO paraProprietarioDTO(Proprietario proprSalvo)
	{
		ProprietarioDTO proprResposta = new ProprietarioDTO();
		proprResposta.setId(proprSalvo.getId());
		proprResposta.setCpf(proprSalvo.getCpf());
		proprResposta.setEmail(proprSalvo.getEmail());
		proprResposta.setEndereco(proprSalvo.getEndereco());
		proprResposta.setIdade(proprSalvo.getIdade());
		proprResposta.setNome(proprSalvo.getNome());
		proprResposta.setTelefone(proprSalvo.getTelefone());
		
		List<ResumoCarroDTO> listaResumoCarro = new ArrayList<>();
		
		for(Carro c : proprSalvo.getListaCarros()) //para cada carro do proprietario, cria um novo ResumoCarroDTO dentro do loop
		{		
			ResumoCarroDTO resumoCarro = new ResumoCarroDTO();
			resumoCarro.setId(c.getId());
			resumoCarro.setModelo(c.getModelo());
			resumoCarro.setPlaca(c.getPlaca());
			listaResumoCarro.add(resumoCarro);
		}
		
		proprResposta.setListaCarros(listaResumoCarro);
	    return proprResposta;
	}
}