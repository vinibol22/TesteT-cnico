package com.br.attornatus.repositories.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.attornatus.model.Endereco;
import com.br.attornatus.model.Pessoa;
import com.br.attornatus.repositories.PessoaRepository;
import com.br.attornatus.repositories.impl.PessoaImpl;

@SpringBootTest
public class PessoaImplTeste {
	@Autowired
	PessoaImpl pessoaImpl;

	@Autowired
	PessoaRepository pessoaRepository;

	@Test
	public void salvarPessoa() {
		Optional<Pessoa> pessoa = pessoaImpl.criarPessoa(new Pessoa());
		assertThat(pessoa).isNotEmpty();

	}

	@Test
	public void buscarTodasPessoas() {
		Pessoa pessoa = new Pessoa(1, "vinicius", "04/10/199");
		pessoaImpl.criarPessoa(pessoa);

		Pessoa pessoa2 = new Pessoa(2, "Barbosa", "24/12/2005");
		pessoaImpl.criarPessoa(pessoa2);

		Pessoa pessoa3 = new Pessoa(3, "nikolas", "05//1970");
		pessoaImpl.criarPessoa(pessoa3);

		Iterable<Pessoa> pessoas2 = pessoaImpl.listarPessoas();

		assertThat(pessoas2).hasSize(3);
	}

	@Test
	public void buscarPessoaPorId() {
		Pessoa pessoa = new Pessoa(1, "vinicius barbosa", "04/10/199");
		pessoaImpl.criarPessoa(pessoa);

		Pessoa pessoa2 = new Pessoa(2, "Barbosa", "24/12/2005");
		pessoaImpl.criarPessoa(pessoa2);

		Pessoa teste = pessoaImpl.buscarPessoaPorId(pessoa.getId()).get();
		assertEquals(teste.getId(), pessoa.getId());
	}

	@Test
	public void atualizarPessoa() {
		Pessoa pessoa = new Pessoa(1, "vinicius", "04/10/199");
		pessoaImpl.criarPessoa(pessoa);

		Pessoa pessoa2 = new Pessoa(2, "Barbosa", "24/12/2005");
		pessoaImpl.criarPessoa(pessoa2);

		Pessoa atualizar = new Pessoa(3, "nikolas", "05//1970");
		pessoaImpl.criarPessoa(atualizar);

		Pessoa teste = pessoaImpl.buscarPessoaPorId(pessoa.getId()).get();

		teste.setId(atualizar.getId());
		teste.setNome(atualizar.getNome());
		teste.setDataNascimento(atualizar.getDataNascimento());

		pessoaImpl.criarPessoa(teste);

		Pessoa checarPessoa = pessoaImpl.buscarPessoaPorId(pessoa.getId()).get();

		assertThat(checarPessoa.getId()).isEqualTo(pessoa.getId()); //

	}

	@Test
	public void buscarPessoaPorNome() {
		Pessoa pessoa6 = new Pessoa(6, "matheus", "04/10/199");
		pessoaImpl.criarPessoa(pessoa6);

		Pessoa pessoa7 = new Pessoa(7, "alaf", "24/12/2005");
		pessoaImpl.criarPessoa(pessoa7);

		Pessoa pessoa8 = new Pessoa(8, "pascoal", "05//1970");
		pessoaImpl.criarPessoa(pessoa8);

		Iterable<Pessoa> pessoas10 = pessoaImpl.buscaPessoaPorNome("matheus");
		assertThat(pessoas10).hasSize(1);

	}

	@Test
	void deletePessoaPorId() {
		Pessoa pessoa4 = new Pessoa(4, "vinicius barbosa", "04/10/199");
		pessoaImpl.criarPessoa(pessoa4);

		Pessoa pessoa5 = new Pessoa(5, "Barbosa", "24/12/2005");
		pessoaImpl.criarPessoa(pessoa5);

		pessoaImpl.excluirPessoa(pessoa4.getId());

		pessoaImpl.excluirPessoa(pessoa5.getId());

		Iterable pessoas = pessoaImpl.listarPessoas();
		assertThat(pessoas).hasSize(5).doesNotContain(pessoa5, pessoa4);
	}
}