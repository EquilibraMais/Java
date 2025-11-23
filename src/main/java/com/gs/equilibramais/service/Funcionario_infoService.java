package com.gs.equilibramais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.equilibramais.dto.Funcionario_infoDto;
import com.gs.equilibramais.mapper.Funcionario_infoMapper;
import com.gs.equilibramais.mapper.Funcionario_infoMapperInterface;
import com.gs.equilibramais.model.Funcionario_info;
import com.gs.equilibramais.repository.Funcionario_infoRepository;

@Service
public class Funcionario_infoService {

	@Autowired
	private Funcionario_infoRepository repF;
	
	@Autowired
	private Funcionario_infoCachingService cacheF;
	
	@Autowired
	private Funcionario_infoMapper mapper;
	
	@Autowired
	private Funcionario_infoMapperInterface mapperInterface;
	
	@Transactional(readOnly = true)
	public Page<Funcionario_infoDto> paginar(PageRequest req){
		
		Page<Funcionario_info> funcionarios_info = cacheF.findAll(req);
	
		return funcionarios_info.map(i -> mapperInterface.toDto(i));
	}
}
