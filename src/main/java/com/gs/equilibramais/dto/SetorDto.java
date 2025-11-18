package com.gs.equilibramais.dto;

import com.gs.equilibramais.model.Empresa;

public class SetorDto {

	private Long id;
	private String descricao;
	private Empresa empresa;
	
	public SetorDto() {}
	
	public SetorDto(Long id, String descricao, Empresa empresa) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}
