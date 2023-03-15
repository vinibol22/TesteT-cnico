package com.br.testeunitario.repositories.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.testeunitario.model.Pessoa;
import com.br.testeunitario.repositories.PessoaRepository;

@Service
public class PessoaImpl {
	@Autowired
	private PessoaRepository pessoaRepository;

	public PessoaImpl(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	public PessoaImpl() {
	}

	public Iterable<Pessoa> listarPessoas() {
		return pessoaRepository.findAll();
	}

	public Optional<Pessoa> buscarPessoaPorId(Long id) {
		return pessoaRepository.findById(id);
	}

	public List<Pessoa> buscaPessoaPorNome(String nome) {
		return pessoaRepository.findAllByNomeContainingIgnoreCase(nome);
	}

	public Optional<Pessoa> criarPessoa(Pessoa pessoa) {
		return Optional.of(pessoaRepository.save(pessoa));
	}

	public Optional<Pessoa> editarPessoa(Pessoa pessoa) {
		return Optional.of(pessoaRepository.save(pessoa));
	}

	public void excluirPessoa(Long id) {
		pessoaRepository.deleteById(id);

	}
}
