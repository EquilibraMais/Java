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
}
