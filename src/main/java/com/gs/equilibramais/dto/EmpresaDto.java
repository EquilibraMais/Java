package com.gs.equilibramais.dto;

public class EmpresaDto {

	private Long id;
	private String nome_empresa;
	
	public EmpresaDto() {}
	
	public EmpresaDto(Long id, String nome_empresa) {
		super();
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
