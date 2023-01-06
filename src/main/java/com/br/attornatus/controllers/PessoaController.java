package com.br.attornatus.controllers;

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

import com.br.attornatus.model.Pessoa;
import com.br.attornatus.repositories.impl.PessoaImpl;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PessoaController {
	@Autowired
	private PessoaImpl pessoaImpl;

	public PessoaController(PessoaImpl pessoaImpl) {
		this.pessoaImpl = pessoaImpl;
	}

	public PessoaController() {
		super();
	}

	@GetMapping("/all")
	public ResponseEntity<Iterable<Pessoa>> getAll() {
		return ResponseEntity.ok(pessoaImpl.listarPessoas());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getById(@PathVariable long id) {
		return pessoaImpl.buscarPessoaPorId(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("nome/{nome}")
	public ResponseEntity<List<Pessoa>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(pessoaImpl.buscaPessoaPorNome(nome));
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Pessoa> postUsuario(@RequestBody Pessoa pessoa) {

		return pessoaImpl.criarPessoa(pessoa).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

	@PutMapping("/alterar/{id}")
	public ResponseEntity<Pessoa> putUsuario(@RequestBody Pessoa pessoa, @PathVariable long id) {

		return pessoaImpl.editarPessoa(pessoa).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@DeleteMapping("/{id}")
	public void deletePessoa(@PathVariable long id) {
		pessoaImpl.excluirPessoa(id);
	}

}
