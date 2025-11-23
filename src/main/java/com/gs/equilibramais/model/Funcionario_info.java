package com.gs.equilibramais.model;

import java.util.Date;

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
@Table(name = "FUNCIONARIO_INFO")
public class Funcionario_info {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Digite um valor para Humor")
	private int humor;
	@NotEmpty(message = "Digite um valor para Energia")
	private int energia;
	@NotEmpty(message = "Digite um valor para Carga")
	private int carga;
	@NotEmpty(message = "Digite um valor para Sono")
	private int sono;
	private String observacao;
	private String historico_medico;
	private Date data;
	@ManyToOne
	@JoinColumn(name= "USUARIO_ID")
	private Usuario usuario;
	
	public Funcionario_info() {}

	public Funcionario_info(Long id, int humor, int energia, int carga, int sono, String observacao,
			String historico_medico, Date data, Usuario usuario) {
		super();
		this.id = id;
		this.humor = humor;
		this.energia = energia;
		this.carga = carga;
		this.sono = sono;
		this.observacao = observacao;
		this.historico_medico = historico_medico;
		this.data = data;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getHumor() {
		return humor;
	}

	public void setHumor(int humor) {
		this.humor = humor;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public int getCarga() {
		return carga;
	}

	public void setCarga(int carga) {
		this.carga = carga;
	}

	public int getSono() {
		return sono;
	}

	public void setSono(int sono) {
		this.sono = sono;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getHistorico_medico() {
		return historico_medico;
	}

	public void setHistorico_medico(String historico_medico) {
		this.historico_medico = historico_medico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
