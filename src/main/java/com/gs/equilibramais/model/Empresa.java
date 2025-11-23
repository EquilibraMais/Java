package com.gs.equilibramais.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "EMPRESA")
public class Empresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Não é permitido a inserção de Empresa sem nome de identificação")
	private String nome_empresa;
	
	public Empresa() {}

	public Empresa(Long id, String nome_empresa) {
		this.id = id;
		this.nome_empresa = nome_empresa;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome_empresa() {
		return nome_empresa;
	}

	public void setNome_empresa(String nome_empresa) {
		this.nome_empresa = nome_empresa;
	}

}
