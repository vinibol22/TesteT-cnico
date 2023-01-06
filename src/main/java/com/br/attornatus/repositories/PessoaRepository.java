package com.br.attornatus.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.attornatus.model.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
	List<Pessoa> findAllByNomeContainingIgnoreCase(String nome);

}
