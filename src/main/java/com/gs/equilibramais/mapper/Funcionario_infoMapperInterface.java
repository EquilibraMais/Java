package com.gs.equilibramais.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.gs.equilibramais.dto.Funcionario_infoDto;
import com.gs.equilibramais.model.Funcionario_info;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface Funcionario_infoMapperInterface {

	Funcionario_infoDto toDto(Funcionario_info funcionario_info);
	
	Funcionario_info toEntity(Funcionario_infoDto toDto);
}
