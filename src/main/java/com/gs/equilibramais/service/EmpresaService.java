package com.gs.equilibramais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.equilibramais.dto.EmpresaDto;
import com.gs.equilibramais.mapper.EmpresaMapper;
import com.gs.equilibramais.mapper.EmpresaMapperInterface;
import com.gs.equilibramais.model.Empresa;
import com.gs.equilibramais.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository repE;
	
	@Autowired
	private EmpresaCachingService cacheE;
	
	@Autowired
	private EmpresaMapper mapper;
	
	@Autowired
	private EmpresaMapperInterface mapperInterface;
	
	@Transactional(readOnly = true)
	public Page<EmpresaDto> paginar(PageRequest req){
		
		Page<Empresa> empresas = cacheE.findAll(req);
	
		return empresas.map(i -> mapperInterface.toDto(i));
	}
}
