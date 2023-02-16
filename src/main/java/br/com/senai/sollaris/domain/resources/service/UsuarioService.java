package br.com.senai.sollaris.domain.resources.service;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.Usuario;
import br.com.senai.sollaris.domain.repository.UsuarioRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.UsuarioDto;

/*
 * ELE É A COZINHA
 * ELE QUE PREPARA
 * 
 * POST, PUT E DELETE (TCL) COMMIT (aprova), ROLLBACK (rejeita, desfaz) 
 * 
 * RESPONSE ENTITY: É O PADRÃO DO SPRING PARA DEVOLUÇÃO DAS RESPOSTAS
 * 
 */

@Service
public class UsuarioService {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
		
	}
	
	public Usuario listarUsuario(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado")); 
		
	}
	
	@Transactional
	public ResponseEntity<Usuario> cadastrarUsuario(UsuarioDto usuarioDto, 
			UriComponentsBuilder uriBuilder) {
		Usuario usuario = new Usuario(usuarioDto);
		usuarioRepository.save(usuario);
		
		URI uri = uriBuilder.path("/api/users/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuario);
	}
	
	@Transactional
	public void alterarUsuario() {
		
	}
	
	@Transactional
	public ResponseEntity<Object> deletarUsuario(Long id) {
		if(usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
			// eu deletei com sucesso mas não tenho nada para te devolver
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
