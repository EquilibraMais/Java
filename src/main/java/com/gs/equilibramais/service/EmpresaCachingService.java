package com.gs.equilibramais.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gs.equilibramais.model.Empresa;
import com.gs.equilibramais.repository.EmpresaRepository;

@Service
public class EmpresaCachingService {
	
	@Autowired
	private EmpresaRepository repE;
	
	@Cacheable(value = "buscaTodasAsEmpresas")
	public List<Empresa> findAll(){
		
		return repE.findAll();
	}
	
	@Cacheable(value = "buscaEmpresaPorId")
	public Optional<Empresa> findById(Long id) {
		return repE.findById(id);
	}
	
	@Cacheable(value = "buscaEmpresaPaginado")
	public Page<Empresa> findAll(PageRequest req){
		return repE.findAll(req);
	}
	
	@CacheEvict(value = {"buscaTodasAsEmpresas", "buscaEmpresaPorId",
						"buscaEmpresaPaginado"}, allEntries = true)
	public void limparCache() {
		System.out.println("Limpando o cache!");
	}
}
