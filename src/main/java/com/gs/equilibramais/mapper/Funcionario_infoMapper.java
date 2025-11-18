package com.gs.equilibramais.mapper;
import org.springframework.stereotype.Component;

import com.gs.equilibramais.dto.Funcionario_infoDto;
import com.gs.equilibramais.model.Funcionario_info;

@Component
public class Funcionario_infoMapper {
	
	public Funcionario_infoDto toDto(Funcionario_info funcionario_info) {
		
		Funcionario_infoDto dto = new Funcionario_infoDto();
	
		dto.setId(funcionario_info.getId());
		dto.setCarga(funcionario_info.getCarga());
		dto.setEnergia(funcionario_info.getEnergia());
		dto.setHumor(funcionario_info.getHumor());
		dto.setObservacao(funcionario_info.getObservacao());
		dto.setSono(funcionario_info.getSono());
		dto.setData(funcionario_info.getData());
		dto.setUsuario(funcionario_info.getUsuario());
		dto.setHistorico_medico(funcionario_info.getHistorico_medico());
		
		return dto;
	}

}
