package com.gs.equilibramais.dto;

import com.gs.equilibramais.model.Setor;

public class UsuarioDto {
	
	private Long id;
	private String nome;
	private String cargo;
	private Setor setor;
	
	public UsuarioDto() {}

	public UsuarioDto(Long id, String nome, String cargo, Setor setor) {
		super();
		this.id = id;
		this.nome = nome;
		this.cargo = cargo;
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

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

}
