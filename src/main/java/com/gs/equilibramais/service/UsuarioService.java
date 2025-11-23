package com.gs.equilibramais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.equilibramais.dto.UsuarioDto;
import com.gs.equilibramais.mapper.UsuarioMapper;
import com.gs.equilibramais.mapper.UsuarioMapperInterface;
import com.gs.equilibramais.model.Usuario;
import com.gs.equilibramais.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repU;
	
	@Autowired
	private UsuarioCachingService cacheU;
	
	@Autowired
	private UsuarioMapper mapper;
	
	@Autowired
	private UsuarioMapperInterface mapperInterface;
	
	@Transactional(readOnly = true)
	public Page<UsuarioDto> paginar(PageRequest req){
		
		Page<Usuario> usuarios = cacheU.findAll(req);
	
		return usuarios.map(i -> mapperInterface.toDto(i));
	}
}
