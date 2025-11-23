package com.gs.equilibramais.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gs.equilibramais.model.Empresa;
import com.gs.equilibramais.model.Usuario;
import com.gs.equilibramais.repository.EmpresaRepository;
import com.gs.equilibramais.repository.UsuarioRepository;
import com.gs.equilibramais.service.EmpresaCachingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class EmpresaController {

	@Autowired
	private EmpresaRepository repE;
	
	
	@Autowired
	private UsuarioRepository repU;
	
	@Autowired
	private EmpresaCachingService cacheE;
	
	@GetMapping("/empresa/index")
	public ModelAndView popularIndex() {

		ModelAndView mv = new ModelAndView("/empresa/index");

		List<Empresa> empresas = cacheE.findAll();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Optional<Usuario> op = repU.findByNome(auth.getName());
		
		if(op.isPresent()) {
			mv.addObject("usuario", op.get());
		}

		mv.addObject("empresas", empresas);
		mv.addObject("lista_empresas", cacheE.findAll());

		return mv;
	}
	
	@GetMapping("/empresa/novo")
	public ModelAndView retornarCadEmpresa() {

		ModelAndView mv = new ModelAndView("/empresa/novo");

		mv.addObject("empresa", new Empresa());
		mv.addObject("lista_empresas", cacheE.findAll());

		return mv;
	}
	
	@PostMapping("/empresa/insere_empresa")
	public ModelAndView cadastrarEmpresa(@Valid Empresa empresa, BindingResult bd) {
		
		if(bd.hasErrors()) {
			
			ModelAndView mv = new ModelAndView("/empresa/novo");
			mv.addObject("empresa", empresa);
			mv.addObject("lista_empresas", cacheE.findAll());
			return mv;
						
		} else {
		
			Empresa empresa_nova = new Empresa();
			empresa_nova.setNome_empresa(empresa.getNome_empresa());
			
			repE.save(empresa_nova);
			cacheE.limparCache();
			
			return new ModelAndView("redirect:/index");
		}
	}
	
	@GetMapping("/empresa/detalhes/{id}")
	public ModelAndView exibirDetalhesEmpresa(HttpServletRequest request, @PathVariable Long id) {
		
		Optional<Empresa> op = cacheE.findById(id);
		
		if(op.isPresent()) {
			
			ModelAndView mv = new ModelAndView("/empresa/detalhes");
			mv.addObject("empresa", op.get());
			mv.addObject("uri", request.getRequestURI());
			return mv;
			  
		} else {
			return new ModelAndView("redirect:/index");
		}
	}
	
	@GetMapping("/empresa/editar/{id}")
	public ModelAndView exibirPaginaEmpresa(@PathVariable Long id){
		
		Optional<Empresa> op = cacheE.findById(id);
		
		if(op.isPresent()) {
			
			ModelAndView mv = new ModelAndView("/empresa/edicao");
			mv.addObject("empresa", op.get());
			mv.addObject("lista_empresas", repE.findAll());
			cacheE.limparCache();
			return mv;
			
		} else {
			return new ModelAndView("redirect:/index");
		}
	}
	
	@PostMapping("/empresa/atualizar_empresa/{id}")
	public ModelAndView atualizarEmpresa(@PathVariable Long id, @Valid Empresa empresa, BindingResult bd) {
		
		if(bd.hasErrors()) {
			
			ModelAndView mv = new ModelAndView("/empresa/edicao");
			mv.addObject("empresa", empresa);
			mv.addObject("lista_empresas", cacheE.findAll());
			return mv;
			
		} else {
			Optional<Empresa> op = cacheE.findById(id);
			
			if(op.isPresent()) {
				
				Empresa empresa_antiga = op.get();
				empresa_antiga.setNome_empresa(empresa.getNome_empresa());
				repE.save(empresa_antiga);
				cacheE.limparCache();
				return new ModelAndView("redirect:/index");
				
			} else {
				return new ModelAndView("redirect:/index");
			}
		}
	}
	
	@GetMapping("/empresa/remover/{id}")
	public ModelAndView removerEmpresa(@PathVariable Long id) {
		
		Optional<Empresa> op = cacheE.findById(id);
		
		if(op.isPresent()) {
			
			repE.deleteById(id);
			cacheE.limparCache();
			
			return new ModelAndView("redirect:/index");
			
		} else {
			return new ModelAndView("redirect:/index");
		}
		
	}
}
