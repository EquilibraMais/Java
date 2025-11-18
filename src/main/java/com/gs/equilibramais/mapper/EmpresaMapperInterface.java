package com.gs.equilibramais.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.gs.equilibramais.dto.EmpresaDto;
import com.gs.equilibramais.model.Empresa;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmpresaMapperInterface {

	EmpresaDto toDto(Empresa empresa);
	
	Empresa toEntity(EmpresaDto toDto);
}
