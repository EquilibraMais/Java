package com.gs.equilibramais.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gs.equilibramais.model.Funcionario_info;
import com.gs.equilibramais.repository.Funcionario_infoRepository;

@Service
public class Funcionario_infoCachingService {

	@Autowired
	private Funcionario_infoRepository repF;
	
	@Cacheable(value = "buscaTodosAsInfosDeFuncionario")
	public List<Funcionario_info> findAll(){
		
		return repF.findAll();
	}
	
	@Cacheable(value = "buscaInfoDeFuncionarioPorId")
	public Optional<Funcionario_info> findById(Long id) {
		return repF.findById(id);
	}
	
	@Cacheable(value = "buscaInfoDeFuncionarioPaginado")
	public Page<Funcionario_info> findAll(PageRequest req){
		return repF.findAll(req);
	}
	
	@CacheEvict(value = {"buscaTodosAsInfosDeFuncionario", "buscaInfoDeFuncionarioPorId",
						"buscaInfoDeFuncionarioPaginado"}, allEntries = true)
	public void limparCache() {
		System.out.println("Limpando o cache!");
	}
}
