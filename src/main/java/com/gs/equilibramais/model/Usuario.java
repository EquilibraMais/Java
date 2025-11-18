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
	@ManyToOne
	@JoinColumn(name= "SETOR_ID")
	private Setor setor;
}
