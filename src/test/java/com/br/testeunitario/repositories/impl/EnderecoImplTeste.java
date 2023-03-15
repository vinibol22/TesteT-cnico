package com.br.testeunitario.repositories.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.testeunitario.model.Endereco;
import com.br.testeunitario.repositories.EnderecoRepository;
import com.br.testeunitario.repositories.impl.EnderecoImpl;

@SpringBootTest
public class EnderecoImplTeste {
	@Autowired
	EnderecoImpl enderecoImpl;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Test
	public void salvarEndereco() {
		Optional<Endereco> endereco = enderecoImpl.criarEndereco(new Endereco());
		assertThat(endereco).isNotEmpty();

	}

	@Test
	public void buscarTodosEnderecos() {
		Endereco endereco = new Endereco(1, "Jardim belém", "03810110", 1003, "São paulo", true);
		enderecoImpl.criarEndereco(endereco);

		Endereco endereco2 = new Endereco(2, "Jardim da conquista", "40401110", 200, "Rio de Janeiro", false);
		enderecoImpl.criarEndereco(endereco2);

		Endereco endereco3 = new Endereco(3, "Boa esperança", "15151540", 01, "Minas Gerais", false);
		enderecoImpl.criarEndereco(endereco3);

		Iterable<Endereco> enderecos2 = enderecoImpl.listarEnderecos();

		assertThat(enderecos2).hasSize(3);
	}

	@Test
	public void buscarEnderecoPorId() {
		Endereco endereco = new Endereco(1, "Jardim belém", "03810110", 1003, "São paulo", true);
		enderecoImpl.criarEndereco(endereco);

		Endereco endereco2 = new Endereco(2, "Jardim da conquista", "40401110", 200, "Rio de Janeiro", false);
		enderecoImpl.criarEndereco(endereco2);

		Endereco teste = enderecoImpl.buscarEnderecoPorId(endereco.getId()).get();
		assertEquals(teste.getId(), endereco.getId());
	}

	@Test
	public void atualizarEndereco() {
		Endereco endereco = new Endereco(1, "Jardim belém", "03810110", 1003, "São paulo", true);
		enderecoImpl.criarEndereco(endereco);

		Endereco endereco2 = new Endereco(2, "Jardim da conquista", "40401110", 200, "Rio de Janeiro", false);
		enderecoImpl.criarEndereco(endereco2);

		Endereco atualizar = new Endereco(3, "Boa esperança", "15151540", 01, "Minas Gerais", false);
		enderecoImpl.criarEndereco(atualizar);

		Endereco teste = enderecoImpl.buscarEnderecoPorId(endereco.getId()).get();

		teste.setId(atualizar.getId());
		teste.setCep(atualizar.getCep());
		teste.setCidade(atualizar.getCidade());
		teste.setLogadouro(atualizar.getLogadouro());
		teste.setNumero(atualizar.getNumero());

		enderecoImpl.criarEndereco(teste);

		Endereco checarEndereco = enderecoImpl.buscarEnderecoPorId(endereco.getId()).get();
		assertThat(checarEndereco.getId()).isEqualTo(endereco.getId()); //

	}

	@Test
	public void deleteEnderecosPorId() {
		Endereco endereco4 = new Endereco(1, "Jardim belém", "03810110", 1003, "São paulo", true);
		enderecoImpl.criarEndereco(endereco4);

		Endereco endereco5 = new Endereco(2, "Jardim da conquista", "40401110", 200, "Rio de Janeiro", false);
		enderecoImpl.criarEndereco(endereco5);
		;

		enderecoImpl.excluirEndereco(endereco4.getId());

		enderecoImpl.excluirEndereco(endereco5.getId());

		Iterable pessoas = enderecoImpl.listarEnderecos();
		assertThat(pessoas).hasSize(1).doesNotContain(endereco5, endereco4);
	}
}