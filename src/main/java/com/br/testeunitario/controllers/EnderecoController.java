package com.br.testeunitario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.testeunitario.model.Endereco;
import com.br.testeunitario.repositories.impl.EnderecoImpl;

@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnderecoController {
	@Autowired
	private EnderecoImpl enderecoImpl;

	public EnderecoController(EnderecoImpl enderecoImpl) {
		super();
		this.enderecoImpl = enderecoImpl;
	}

	public EnderecoController() {
		super();
	}

	@GetMapping("/todosenderecos")
	public ResponseEntity<List<Endereco>> getAll() {
		return ResponseEntity.ok(enderecoImpl.listarEnderecos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> getById(@PathVariable long id) {
		return enderecoImpl.buscarEnderecoPorId(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/registrar")
	public ResponseEntity<Endereco> postEndereco(@RequestBody Endereco endereco) {

		return enderecoImpl.criarEndereco(endereco).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

	@PutMapping("/alterar/{id}")
	public ResponseEntity<Endereco> putEndereco(@RequestBody Endereco endereco, @PathVariable long id) {

		return enderecoImpl.editarPessoa(endereco).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@DeleteMapping("/{id}")
	public void deleteEndereco(@PathVariable long id) {
		enderecoImpl.excluirEndereco(id);
	}
}
