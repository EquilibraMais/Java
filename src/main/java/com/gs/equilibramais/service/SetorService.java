package com.gs.equilibramais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.equilibramais.dto.SetorDto;
import com.gs.equilibramais.mapper.SetorMapper;
import com.gs.equilibramais.mapper.SetorMapperInterface;
import com.gs.equilibramais.model.Setor;
import com.gs.equilibramais.repository.SetorRepository;

@Service
public class SetorService {

	@Autowired
	private SetorRepository repS;
	
	@Autowired
	private SetorCachingService cacheS;
	
	@Autowired
	private SetorMapper mapper;
	
	@Autowired
	private SetorMapperInterface mapperInterface;
	
	@Transactional(readOnly = true)
	public Page<SetorDto> paginar(PageRequest req){
		
		Page<Setor> setores = cacheS.findAll(req);
	
		return setores.map(i -> mapperInterface.toDto(i));
	}
}
