package com.gs.equilibramais.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gs.equilibramais.model.Usuario;
import com.gs.equilibramais.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class UsuarioController {

	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UsuarioRepository repU;

	@GetMapping("/login")
	public ModelAndView logar() {
		return new ModelAndView("/login");
	}
	
	@GetMapping("/index")
	public ModelAndView popularIndexMenuPrincipal() {

		ModelAndView mv = new ModelAndView("/home/index");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Optional<Usuario> op = repU.findByNome(auth.getName());
		
		if(op.isPresent()) {
			mv.addObject("usuario", op.get());
		}

		return mv;
	}
	
	@GetMapping("/acesso_negado")
	public ModelAndView acessoNegado() {
	    return new ModelAndView("acesso_negado");
	}
	
	@GetMapping("/usuario/index")
	public ModelAndView popularIndex() {

		ModelAndView mv = new ModelAndView("/usuario/index");

		List<Usuario> usuarios = repU.findAll();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Optional<Usuario> op = repU.findByNome(auth.getName());
		
		if(op.isPresent()) {
			mv.addObject("usuario", op.get());
		}

		mv.addObject("usuarios", usuarios);

		return mv;
	}
	
	@GetMapping("/usuario/novo")
	public ModelAndView retornarCadUsuario() {

		ModelAndView mv = new ModelAndView("/usuario/novo");

		mv.addObject("usuario", new Usuario());

		return mv;
	}
	
	@PostMapping("/usuario/insere_usuario")
	public ModelAndView inserirUsuario(Usuario usuario) {

		usuario.setSenha(encoder.encode(usuario.getSenha()));

		repU.save(usuario);

		return new ModelAndView("redirect:/index");
	}
	
	@GetMapping("/usuario/detalhes/{id}")
	public ModelAndView exibirDetalhesUsuario(HttpServletRequest request, @PathVariable Long id) {
		
		Optional<Usuario> op = repU.findById(id);
		
		if(op.isPresent()) {
			
			ModelAndView mv = new ModelAndView("/usuario/detalhes");
			mv.addObject("usuario", op.get());
			mv.addObject("uri", request.getRequestURI());
			return mv;
			  
		} else {
			return new ModelAndView("redirect:/index");
		}
	}
	
	@GetMapping("/usuario/editar/{id}")
	public ModelAndView exibirPaginaEdicao(@PathVariable Long id){
		
		Optional<Usuario> op = repU.findById(id);
		
		if(op.isPresent()) {
			
			ModelAndView mv = new ModelAndView("/usuario/edicao");
			mv.addObject("usuario", op.get());
			return mv;
			
		} else {
			return new ModelAndView("redirect:/index");
		}
	}
	
	@PostMapping("/usuario/atualizar_usuario/{id}")
	public ModelAndView atualizarUsuario(@PathVariable Long id, @Valid Usuario usuario, BindingResult bd) {
		
		if(bd.hasErrors()) {
			
			ModelAndView mv = new ModelAndView("/usuario/edicao");
			mv.addObject("usuario", usuario);
			return mv;
			
		} else {
			Optional<Usuario> op = repU.findById(id);
			
			if(op.isPresent()) {
				
				Usuario usuario_antigo = op.get();
				usuario_antigo.setCargo(usuario.getCargo());
				usuario_antigo.setNome(usuario.getNome());
				usuario_antigo.setSenha(encoder.encode(usuario.getSenha()));
				usuario_antigo.setSetor(usuario.getSetor());
				if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
					usuario_antigo.setSenha(encoder.encode(usuario.getSenha()));
					}
				repU.save(usuario_antigo);
				return new ModelAndView("redirect:/index");
				
			} else {
				return new ModelAndView("redirect:/index");
			}
		}
	}
	
	@GetMapping("/usuario/remover/{id}")
	public ModelAndView removerUsuario(@PathVariable Long id) {
		
		Optional<Usuario> op = repU.findById(id);
		
		if(op.isPresent()) {
			
			repU.deleteById(id);
			
			return new ModelAndView("redirect:/index");
			
		} else {
			return new ModelAndView("redirect:/index");
		}
		
	}
}
