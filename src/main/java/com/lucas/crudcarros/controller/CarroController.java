package com.lucas.crudcarros.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.crudcarros.dto.CarroDTO;
import com.lucas.crudcarros.model.Carro;
import com.lucas.crudcarros.repository.CarroRepository;
import com.lucas.crudcarros.repository.ProprietarioRepository;
import com.lucas.crudcarros.service.CarroService;

@RestController //Essa classe vai devolver dados direto pro cliente, sem ela, o Spring ia esperar que você retornasse uma página HTML, tipo um JSP ou Thymeleaf
@CrossOrigin("*") //Permite requisições de outros domínios/origens.
@RequestMapping("/carros") //Define o prefixo da rota pra todos os métodos desse controller.
public class CarroController {
	
	@Autowired
	CarroService carroService;
	
	@Autowired
	CarroRepository carroR;
	
	@Autowired
	ProprietarioRepository proprR;

	@PostMapping
	public ResponseEntity<CarroDTO> cadastrarCarro(@RequestBody CarroDTO carroDTO)
	{
		/*Carro carro = carroService.paraCarro(carroDTO);
		carro.setProprietario(proprR.findByCpf(carroDTO.getCpfProprietario()));
		Carro carroSalvo = carroR.save(carro);
		return ResponseEntity.ok(carroService.paraCarroDTO(carroSalvo));*/
		CarroDTO carroSalvo = carroService.salvarCarro(carroDTO);
		return ResponseEntity.ok(carroSalvo);
	}
	
	@GetMapping
	public ResponseEntity<List<CarroDTO>> listarTodosCarros()
	{
		List<Carro> listaCarros = carroR.findAll();
		List<CarroDTO> listaCarrosDTO = new ArrayList<>();
		
		for(Carro c : listaCarros)
		{
			CarroDTO dto = carroService.paraCarroDTO(c);
			listaCarrosDTO.add(dto);
		}
		
		return ResponseEntity.ok(listaCarrosDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Carro> deletarCarro(@PathVariable Integer id)
	{
		Carro carro = new Carro();
		carro.setId(id);
		carroService.removerCarro(carro);
		return ResponseEntity.status(204).build(); //204 No Content indica que a remoção foi feita com sucesso e não há nada para retornar no corpo
	}
	
	@GetMapping("/marca")
	public ResponseEntity<List<CarroDTO>> listarCarrosMarca(@RequestParam String nome)
	{
		Carro carro = new Carro();
		carro.setMarca(nome);
		List<Carro> listaDeCarrosMarca = carroService.listarCarroPorMarca(carro);
		
		if(listaDeCarrosMarca.isEmpty())
		{
			throw new IllegalArgumentException("Nenhum carro encontrado com essa marca");
		}
		
		List<CarroDTO> listaDTO = new ArrayList<>();
		
		for(Carro c : listaDeCarrosMarca)
		{
			CarroDTO dto = carroService.paraCarroDTO(c);
			listaDTO.add(dto);
		}
		
		return ResponseEntity.ok(listaDTO);
	}
}
