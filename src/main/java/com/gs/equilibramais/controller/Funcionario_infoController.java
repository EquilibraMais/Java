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

import com.gs.equilibramais.model.Funcionario_info;
import com.gs.equilibramais.model.Usuario;
import com.gs.equilibramais.repository.Funcionario_infoRepository;
import com.gs.equilibramais.repository.UsuarioRepository;
import com.gs.equilibramais.service.Funcionario_infoCachingService;
import com.gs.equilibramais.service.UsuarioCachingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class Funcionario_infoController {
	@Autowired
	private Funcionario_infoRepository repF;
	
	@Autowired
	private Funcionario_infoCachingService cacheF;
	
	@Autowired
	private UsuarioRepository repU;
	
	@Autowired
	private UsuarioCachingService cacheU;
	
	@GetMapping("/funcionario_info/index")
	public ModelAndView popularIndex() {

		ModelAndView mv = new ModelAndView("/funcionario_info/index");

		List<Funcionario_info> funcionarios_infos = cacheF.findAll();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Optional<Usuario> op = repU.findByNome(auth.getName());
		
		if(op.isPresent()) {
			mv.addObject("usuario", op.get());
		}

		mv.addObject("funcionarios_info", funcionarios_infos);
		mv.addObject("lista_usuarios", cacheU.findAll());

		System.out.println(funcionarios_infos);
		
		return mv;
	}
	
	@GetMapping("/funcionario_info/novo")
	public ModelAndView retornarCadFuncionario_info() {

		ModelAndView mv = new ModelAndView("/funcionario_info/novo");

		mv.addObject("funcionario_info", new Funcionario_info());
		mv.addObject("lista_funcionarios_infos", cacheF.findAll());

		return mv;
	}
	
	@PostMapping("/funcionario_info/insere_funcionario_info")
	public ModelAndView cadastrarFuncionario_info(@Valid Funcionario_info funcionario_info, BindingResult bd) {
		
		if(bd.hasErrors()) {
			
			ModelAndView mv = new ModelAndView("/funcionario_info/novo");
			mv.addObject("funcionario_info", funcionario_info);
			mv.addObject("lista_usuarios", cacheU.findAll());
			return mv;
						
		} else {
		
			Funcionario_info funcionario_info_novo = new Funcionario_info();
			funcionario_info_novo.setCarga(funcionario_info.getCarga());
			funcionario_info_novo.setData(funcionario_info.getData());
			funcionario_info_novo.setEnergia(funcionario_info.getEnergia());
			funcionario_info_novo.setHistorico_medico(funcionario_info.getHistorico_medico());
			funcionario_info_novo.setHumor(funcionario_info.getHumor());
			funcionario_info_novo.setObservacao(funcionario_info.getObservacao());
			funcionario_info_novo.setSono(funcionario_info.getSono());
			funcionario_info_novo.setUsuario(funcionario_info.getUsuario());
			
			repF.save(funcionario_info_novo);
			cacheF.limparCache();
			
			return new ModelAndView("redirect:/index");
		}
	}
	
	@GetMapping("/funcionario_info/detalhes/{id}")
	public ModelAndView exibirDetalhesFuncionario_info(HttpServletRequest request, @PathVariable Long id) {
		
		Optional<Funcionario_info> op = cacheF.findById(id);
		
		if(op.isPresent()) {
			
			ModelAndView mv = new ModelAndView("/funcionario_info/detalhes");
			mv.addObject("funcionario_info", op.get());
			mv.addObject("uri", request.getRequestURI());
			return mv;
			  
		} else {
			return new ModelAndView("redirect:/index");
		}
	}
	
	@GetMapping("/funcionario_info/editar/{id}")
	public ModelAndView exibirPaginaFuncionario_info(@PathVariable Long id){
		
		Optional<Funcionario_info> op = cacheF.findById(id);
		
		if(op.isPresent()) {
			
			ModelAndView mv = new ModelAndView("/funcionario_info/edicao");
			mv.addObject("funcionario_info", op.get());
			mv.addObject("lista_usuarios", repU.findAll());
			cacheF.limparCache();
			return mv;
			
		} else {
			return new ModelAndView("redirect:/index");
		}
	}
	
	@PostMapping("/funcionario_info/atualizar_funcionario_info/{id}")
	public ModelAndView atualizarFuncionario_info(@PathVariable Long id, @Valid Funcionario_info funcionario_info, BindingResult bd) {
		
		if(bd.hasErrors()) {
			
			ModelAndView mv = new ModelAndView("/funcionario_info/edicao");
			mv.addObject("funcionario_info", funcionario_info);
			mv.addObject("lista_usuarios", cacheU.findAll());
			return mv;
			
		} else {
			Optional<Funcionario_info> op = cacheF.findById(id);
			
			if(op.isPresent()) {
				
				Funcionario_info funcionario_info_antigo = op.get();
				funcionario_info_antigo.setCarga(funcionario_info.getCarga());
				funcionario_info_antigo.setData(funcionario_info.getData());
				funcionario_info_antigo.setEnergia(funcionario_info.getEnergia());
				funcionario_info_antigo.setHistorico_medico(funcionario_info.getHistorico_medico());
				funcionario_info_antigo.setHumor(funcionario_info.getHumor());
				funcionario_info_antigo.setObservacao(funcionario_info.getObservacao());
				funcionario_info_antigo.setSono(funcionario_info.getSono());
				funcionario_info_antigo.setUsuario(funcionario_info.getUsuario());
				repF.save(funcionario_info_antigo);
				cacheF.limparCache();
				return new ModelAndView("redirect:/index");
				
			} else {
				return new ModelAndView("redirect:/index");
			}
		}
	}
	
	@GetMapping("/funcionario_info/remover/{id}")
	public ModelAndView removerFuncionario_info(@PathVariable Long id) {
		
		Optional<Funcionario_info> op = cacheF.findById(id);
		
		if(op.isPresent()) {
			
			repF.deleteById(id);
			cacheF.limparCache();
			
			return new ModelAndView("redirect:/index");
			
		} else {
			return new ModelAndView("redirect:/index");
		}
		
	}
}
