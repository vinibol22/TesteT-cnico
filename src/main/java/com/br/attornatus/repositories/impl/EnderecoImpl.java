package com.br.attornatus.repositories.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.attornatus.model.Endereco;
import com.br.attornatus.repositories.EnderecoRepository;

@Service
public class EnderecoImpl {
	@Autowired
	private EnderecoRepository enderecoRepository;

	public EnderecoImpl(EnderecoRepository enderecoRepository) {
		this.enderecoRepository = enderecoRepository;
	}

	public List<Endereco> listarEnderecos() {
		return enderecoRepository.findAll();
	}

	public EnderecoImpl() {
		super();
	}

	public Optional<Endereco> criarEndereco(Endereco endereco) {
		return Optional.of(enderecoRepository.save(endereco));
	}

	public Optional<Endereco> buscarEnderecoPorId(Long id) {
		return enderecoRepository.findById(id);
	}

	public Optional<Endereco> editarPessoa(Endereco endereco) {
		return Optional.of(enderecoRepository.save(endereco));
	}

	public void excluirEndereco(Long id) {
		enderecoRepository.deleteById(id);

	}
}
