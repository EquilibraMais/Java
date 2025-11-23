package com.gs.equilibramais.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gs.equilibramais.model.Setor;
import com.gs.equilibramais.repository.SetorRepository;

@Service
public class SetorCachingService {

	@Autowired
	private SetorRepository repS;
	
	@Cacheable(value = "buscaTodosOsSetores")
	public List<Setor> findAll(){
		
		return repS.findAll();
	}
	
	@Cacheable(value = "buscaSetorPorId")
	public Optional<Setor> findById(Long id) {
		return repS.findById(id);
	}
	
	@Cacheable(value = "buscaSetorPaginado")
	public Page<Setor> findAll(PageRequest req){
		return repS.findAll(req);
	}
	
	@CacheEvict(value = {"buscaTodosOsSetores", "buscaSetorPorId",
						"buscaSetorPaginado"}, allEntries = true)
	public void limparCache() {
		System.out.println("Limpando o cache!");
	}
}
