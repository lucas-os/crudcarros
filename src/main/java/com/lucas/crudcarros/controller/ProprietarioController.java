package com.lucas.crudcarros.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.crudcarros.dto.ProprietarioDTO;
import com.lucas.crudcarros.model.Proprietario;
import com.lucas.crudcarros.service.ProprietarioService;

import jakarta.validation.Valid;


@RestController //Essa classe vai devolver dados direto pro cliente, sem ela, o Spring ia esperar que você retornasse uma página HTML, tipo um JSP ou Thymeleaf
@CrossOrigin("*") //Permite requisições de outros domínios/origens.
@RequestMapping("/proprietarios") //Define o prefixo da rota pra todos os métodos desse controller.
public class ProprietarioController {
	
	@Autowired
	ProprietarioService proprietarioService;
	
	
	@PostMapping
	public ResponseEntity<ProprietarioDTO> cadastrarProprietario(@Valid @RequestBody ProprietarioDTO dto)
	{
		ProprietarioDTO proprSalvo = proprietarioService.salvarProprietario(dto);
		return ResponseEntity.ok(proprSalvo);
	}
	
	@GetMapping
	public ResponseEntity<List<ProprietarioDTO>> listarTodosProprietarios()
	{
		List<ProprietarioDTO> listaDeProprietarios = proprietarioService.listarProprietarios();
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
	
	@PutMapping("/{id}")
	public ResponseEntity<ProprietarioDTO> alterarProprietario(@PathVariable Integer id, @Valid @RequestBody ProprietarioDTO dto)
	{
		ProprietarioDTO proprAlterado = proprietarioService.editarProprietario(id, dto);
		return ResponseEntity.ok(proprAlterado);
	}
	
	@GetMapping("/cpf")
	public ResponseEntity<ProprietarioDTO> listarPorCpf(@RequestParam String cpf) {
	    ProprietarioDTO proprietario = proprietarioService.listarProprietarioPorCpf(cpf);

	    if (proprietario == null) {
	        return ResponseEntity.notFound().build();
	    }

	    return ResponseEntity.ok(proprietario);
	}
	
	@GetMapping("/nome")
	public ResponseEntity<List<ProprietarioDTO>> listarProprietariosNome(@RequestParam String nome)
	{
		List<ProprietarioDTO> listaDeProprietariosNome = proprietarioService.listarProprietarioPorNome(nome);
		
		if(listaDeProprietariosNome.isEmpty())
		{
			throw new IllegalArgumentException("Nenhuma pessoa encontrada com esse nome");
		}
		
		return ResponseEntity.ok(listaDeProprietariosNome);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProprietarioDTO> buscarProprietarioPorId(@PathVariable Integer id) {
	    Optional<Proprietario> propr = proprietarioService.buscarPorId(id);
	    if(propr.isPresent()) {
	    	ProprietarioDTO dto = proprietarioService.paraProprietarioDTO(propr.get());
	        return ResponseEntity.ok(dto);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
