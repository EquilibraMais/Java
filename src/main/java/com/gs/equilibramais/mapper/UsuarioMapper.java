package com.gs.equilibramais.mapper;

import org.springframework.stereotype.Component;

import com.gs.equilibramais.dto.UsuarioDto;
import com.gs.equilibramais.model.Usuario;

@Component
public class UsuarioMapper {
	
	public UsuarioDto toDto(Usuario usuario) {
		
		UsuarioDto dto = new UsuarioDto();
		
		dto.setId(usuario.getId());
		dto.setCargo(usuario.getCargo());
		dto.setNome(usuario.getNome());
		dto.setSetor(usuario.getSetor());
		
		return dto;
	}

}
