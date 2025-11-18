package com.gs.equilibramais.mapper;

import org.springframework.stereotype.Component;

import com.gs.equilibramais.dto.SetorDto;
import com.gs.equilibramais.model.Setor;

@Component
public class SetorMapper {
	
	public SetorDto toDto(Setor setor) {
		
		SetorDto dto = new SetorDto();
		
		dto.setId(setor.getId());
		dto.setDescricao(setor.getDescricao());
		dto.setEmpresa(setor.getEmpresa());
		
		return dto;
	}

}
