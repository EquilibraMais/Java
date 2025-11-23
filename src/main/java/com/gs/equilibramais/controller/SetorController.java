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

import com.gs.equilibramais.model.Setor;
import com.gs.equilibramais.model.Usuario;
import com.gs.equilibramais.repository.EmpresaRepository;
import com.gs.equilibramais.repository.SetorRepository;
import com.gs.equilibramais.repository.UsuarioRepository;
import com.gs.equilibramais.service.EmpresaCachingService;
import com.gs.equilibramais.service.SetorCachingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class SetorController {

	@Autowired
	private SetorRepository repS;
	
	@Autowired
	private SetorCachingService cacheS;
	
	@Autowired
	private UsuarioRepository repU;
	
	@Autowired
	private EmpresaRepository repE;
	
	@Autowired
	private EmpresaCachingService cacheE;
	
	@GetMapping("/setor/index")
	public ModelAndView popularIndex() {

		ModelAndView mv = new ModelAndView("/setor/index");

		List<Setor> setores = cacheS.findAll();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Optional<Usuario> op = repU.findByNome(auth.getName());
		
		if(op.isPresent()) {
			mv.addObject("usuario", op.get());
		}

		mv.addObject("setores", setores);
		mv.addObject("lista_setores", cacheS.findAll());

		return mv;
	}
	
	@GetMapping("/setor/novo")
	public ModelAndView retornarCadSetor() {

		ModelAndView mv = new ModelAndView("/setor/novo");

		mv.addObject("setor", new Setor());
		mv.addObject("lista_setores", cacheS.findAll());

		return mv;
	}
	
	@PostMapping("/setor/insere_setor")
	public ModelAndView cadastrarSetor(@Valid Setor setor, BindingResult bd) {
		
		if(bd.hasErrors()) {
			
			ModelAndView mv = new ModelAndView("/setor/novo");
			mv.addObject("setor", setor);
			mv.addObject("lista_setores", cacheS.findAll());
			return mv;
						
		} else {
		
			Setor setor_novo = new Setor();
			setor_novo.setDescricao(setor.getDescricao());
			setor_novo.setEmpresa(setor.getEmpresa());
			
			repS.save(setor_novo);
			cacheS.limparCache();
			
			return new ModelAndView("redirect:/index");
		}
	}
	
	@GetMapping("/setor/detalhes/{id}")
	public ModelAndView exibirDetalhesSetor(HttpServletRequest request, @PathVariable Long id) {
		
		Optional<Setor> op = cacheS.findById(id);
		
		if(op.isPresent()) {
			
			ModelAndView mv = new ModelAndView("/setor/detalhes");
			mv.addObject("setor", op.get());
			mv.addObject("uri", request.getRequestURI());
			return mv;
			  
		} else {
			return new ModelAndView("redirect:/index");
		}
	}
	
	@GetMapping("/setor/editar/{id}")
	public ModelAndView exibirPaginaSetor(@PathVariable Long id){
		
		Optional<Setor> op = cacheS.findById(id);
		
		if(op.isPresent()) {
			
			ModelAndView mv = new ModelAndView("/setor/edicao");
			mv.addObject("setor", op.get());
			mv.addObject("lista_setores", repS.findAll());
			cacheS.limparCache();
			return mv;
			
		} else {
			return new ModelAndView("redirect:/index");
		}
	}
	
	@PostMapping("/setor/atualizar_setor/{id}")
	public ModelAndView atualizarSetor(@PathVariable Long id, @Valid Setor setor, BindingResult bd) {
		
		if(bd.hasErrors()) {
			
			ModelAndView mv = new ModelAndView("/setor/edicao");
			mv.addObject("setor", setor);
			mv.addObject("lista_setores", cacheS.findAll());
			return mv;
			
		} else {
			Optional<Setor> op = cacheS.findById(id);
			
			if(op.isPresent()) {
				
				Setor setor_antigo = op.get();
				setor_antigo.setDescricao(setor.getDescricao());
				setor_antigo.setEmpresa(setor.getEmpresa());
				repS.save(setor_antigo);
				cacheS.limparCache();
				return new ModelAndView("redirect:/index");
				
			} else {
				return new ModelAndView("redirect:/index");
			}
		}
	}
	
	@GetMapping("/setor/remover/{id}")
	public ModelAndView removerSetor(@PathVariable Long id) {
		
		Optional<Setor> op = cacheS.findById(id);
		
		if(op.isPresent()) {
			
			repS.deleteById(id);
			cacheS.limparCache();
			
			return new ModelAndView("redirect:/index");
			
		} else {
			return new ModelAndView("redirect:/index");
		}
		
	}
}
