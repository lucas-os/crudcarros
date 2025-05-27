package com.lucas.crudcarros.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.lucas.crudcarros.model.Carro;
import com.lucas.crudcarros.service.CarroService;

@RestController //Essa classe vai devolver dados direto pro cliente, sem ela, o Spring ia esperar que você retornasse uma página HTML, tipo um JSP ou Thymeleaf
@CrossOrigin("*") //Permite requisições de outros domínios/origens.
@RequestMapping("/carros") //Define o prefixo da rota pra todos os métodos desse controller.
public class CarroController {
	
	@Autowired
	CarroService carroService;
	
	
	@PostMapping
	public ResponseEntity<Carro> cadastrarCarro(@RequestBody Carro carro)
	{
		carroService.salvarCarro(carro);
		return ResponseEntity.status(201).body(carro);
	}
	
	@GetMapping
	public ResponseEntity<List<Carro>> listarTodosCarros(Carro carro)
	{
		List<Carro> listaDeCarros = carroService.listarCarros();
		return ResponseEntity.ok(listaDeCarros);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Carro> deletarCarro(@PathVariable Integer id)
	{
		Carro carro = new Carro();
		carro.setId(id);
		carroService.removerCarro(carro);
		return ResponseEntity.status(204).build(); //204 No Content indica que a remoção foi feita com sucesso e não há nada para retornar no corpo
	}
}
