package com.gs.equilibramais.mapper;

import org.springframework.stereotype.Component;

import com.gs.equilibramais.dto.EmpresaDto;
import com.gs.equilibramais.model.Empresa;

@Component
public class EmpresaMapper {

	public EmpresaDto toDto(Empresa empresa) {
		
		EmpresaDto dto = new EmpresaDto();
		dto.setId(empresa.getId());
		dto.setNome_empresa(empresa.getNome_empresa());
		
		return dto;
	}
}
