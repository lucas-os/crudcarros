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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lucas.crudcarros.model.Proprietario;
import com.lucas.crudcarros.service.ProprietarioService;


@RestController //Essa classe vai devolver dados direto pro cliente, sem ela, o Spring ia esperar que você retornasse uma página HTML, tipo um JSP ou Thymeleaf
@CrossOrigin("*") //Permite requisições de outros domínios/origens.
@RequestMapping("/proprietarios") //Define o prefixo da rota pra todos os métodos desse controller.
public class ProprietarioController {
	
	@Autowired
	ProprietarioService proprietarioService;
	
	
	@PostMapping
	public ResponseEntity<Proprietario> cadastrarProprietario(@RequestBody Proprietario proprietario)
	{
		proprietarioService.salvarProprietario(proprietario);
		return ResponseEntity.status(201).body(proprietario);
	}
	
	@GetMapping
	public ResponseEntity<List<Proprietario>> listarTodosProprietarios(Proprietario proprietario)
	{
		List<Proprietario> listaDeProprietarios = proprietarioService.listarProprietarios();
		return ResponseEntity.ok(listaDeProprietarios);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Proprietario> deletarProprietario(@PathVariable Integer id)
	{
		Proprietario proprietario = new Proprietario();
		proprietario.setId(id);
		proprietarioService.removerProprietario(proprietario);
		return ResponseEntity.status(204).build(); //204 No Content indica que a remoção foi feita com sucesso e não há nada para retornar no corpo
	}
	
	@GetMapping("/nome")
	public ResponseEntity<List<Proprietario>> listarProprietariosNome(@RequestParam String nome)
	{
		Proprietario proprietario = new Proprietario();
		proprietario.setNome(nome);
		List<Proprietario> listaDeProprietariosNome = proprietarioService.listarProprietarioPorNome(proprietario);
		
		if(listaDeProprietariosNome.isEmpty())
		{
			throw new IllegalArgumentException("Nenhuma pessoa encontrada com esse nome");
		}
		
		return ResponseEntity.ok(listaDeProprietariosNome);
	}
}
