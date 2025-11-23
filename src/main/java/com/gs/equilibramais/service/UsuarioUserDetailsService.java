package com.gs.equilibramais.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gs.equilibramais.model.Usuario;
import com.gs.equilibramais.repository.UsuarioRepository;

@Service
public class UsuarioUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repU;

    @Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		
		Usuario usuario = repU.findByNome(nome).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!") );
		
		return User.withUsername(usuario.getNome())
		           .password(usuario.getSenha())
		           .authorities(Collections.emptyList())
		           .build();
	}
}
