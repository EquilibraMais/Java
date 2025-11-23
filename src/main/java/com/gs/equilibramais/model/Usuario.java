package com.gs.equilibramais.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "USUARIO")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Não é permitido a inserção de Usuario sem nome")
	private String nome;
	@NotEmpty(message = "Não é permitido a inserção de Usuario sem cargo")
	private String cargo;
	@NotEmpty(message = "Não é permitido a inserção de Usuario sem senha")
	private String senha;
	@ManyToOne
	@JoinColumn(name= "SETOR_ID")
	private Setor setor;
	
	public Usuario() {}

	public Usuario(Long id, String nome, String cargo, String senha, Setor setor) {
		super();
		this.id = id;
		this.nome = nome;
		this.cargo = cargo;
		this.senha = senha;
		this.setor = setor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
}
