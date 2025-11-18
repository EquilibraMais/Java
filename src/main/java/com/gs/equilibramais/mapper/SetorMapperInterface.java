package com.gs.equilibramais.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.gs.equilibramais.dto.SetorDto;
import com.gs.equilibramais.model.Setor;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SetorMapperInterface {
	
	SetorDto toDto(Setor setor);
	
	Setor toEntity(SetorDto toDto);
}
