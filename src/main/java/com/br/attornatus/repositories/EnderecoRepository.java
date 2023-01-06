package com.br.attornatus.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.attornatus.model.Endereco;
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long>{
}
