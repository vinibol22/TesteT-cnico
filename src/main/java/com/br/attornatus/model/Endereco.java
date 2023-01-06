package com.br.attornatus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_endereco")
public class Endereco {

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	private String logadouro;
	private String cep;
	private double numero;
	private String cidade;
	private boolean principalEndereco;

	
	@ManyToOne
	@JsonIgnoreProperties("endereco")
	private Pessoa pessoa;

	public Endereco(long id, String logadouro, String cep, double numero, String cidade, boolean principalEndereco,
			Pessoa pessoa) {
		super();
		Id = id;
		this.logadouro = logadouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
		this.principalEndereco = principalEndereco;
		this.pessoa = pessoa;
	}
	

	public Endereco(long id, String logadouro, String cep, double numero, String cidade, boolean principalEndereco) {
		super();
		Id = id;
		this.logadouro = logadouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
		this.principalEndereco = principalEndereco;
	}


	public Endereco() {
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getLogadouro() {
		return logadouro;
	}

	public void setLogadouro(String logadouro) {
		this.logadouro = logadouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public double getNumero() {
		return numero;
	}

	public void setNumero(double numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public boolean isPrincipalEndereco() {
		return principalEndereco;
	}

	public void setPrincipalEndereco(boolean principalEndereco) {
		this.principalEndereco = principalEndereco;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
}
