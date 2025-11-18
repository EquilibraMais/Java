package com.gs.equilibramais.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.gs.equilibramais.dto.UsuarioDto;
import com.gs.equilibramais.model.Usuario;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapperInterface {
	
	UsuarioDto toDto(Usuario usuario);
	
	Usuario toEntity(UsuarioDto toDto);

}
