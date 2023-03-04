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
import br.com.senai.sollaris.domain.resources.dtos.input.PutUsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.input.UsuarioDto;

import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioPut;
import br.com.senai.sollaris.domain.resources.service.exceptions.EmailEmUsoException;
import br.com.senai.sollaris.domain.resources.service.exceptions.ObjetoNaoEncontradoException;

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
	
		public List<ReturnUsuarioDto> listarUsuarios() {
		return usuarioRepository.findAll()
				.stream()
				.map(usuario -> new ReturnUsuarioDto(usuario))
				.toList();
		
	}
	
	public Usuario listarUsuario(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Usuário não encontrado")); 
		
	}
	
	@Transactional
	public ResponseEntity<ReturnUsuarioDto> cadastrarUsuario(UsuarioDto usuarioDto, 
			UriComponentsBuilder uriBuilder) {
		validarEmail(usuarioDto);
		
		Usuario usuario = new Usuario(usuarioDto);
		usuarioRepository.save(usuario);
		
		URI uri = uriBuilder.path("/api/users/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReturnUsuarioDto(usuario));

	}
	
	@Transactional
	public ResponseEntity<ReturnUsuarioPut> alterarUsuario(Long id, @Valid PutUsuarioDto usuarioDto) {

		validarEmail(usuarioDto);

		Usuario usuario = buscarUsuario(id);
		
		usuario.atualizarInformacoes(id, usuarioDto);
		
		return ResponseEntity.ok(new ReturnUsuarioPut(usuario));
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
	
	private Usuario buscarUsuario(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Usuário não encontrado")); 
	}
	
	private void validarEmail(UsuarioDto usuario) {
		boolean email_em_uso = usuarioRepository.findByEmail(usuario.getEmail())
		.stream()
		.anyMatch(usuarioSGDB -> !usuarioSGDB.equals(new Usuario(usuario)));
		
		if (email_em_uso)
			throw new EmailEmUsoException("Email em uso, tente novamente!");
	}
	
	private void validarEmail(PutUsuarioDto usuario) {
		boolean email_em_uso = usuarioRepository.findByEmail(usuario.getEmail())
				.stream()
				.anyMatch(usuarioSGDB -> !usuarioSGDB.equals(new Usuario(usuario)));
				
				if (email_em_uso)
					throw new EmailEmUsoException("Email em uso, tente novamente!");
		
	}

}
