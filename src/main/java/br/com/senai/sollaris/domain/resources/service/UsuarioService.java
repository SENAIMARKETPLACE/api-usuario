package br.com.senai.sollaris.domain.resources.service;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.Usuario;
import br.com.senai.sollaris.domain.repository.UsuarioRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.PutUsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.input.UsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.input.UsuarioLogin;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioPut;
import br.com.senai.sollaris.domain.resources.service.exceptions.ObjetoNaoEncontradoException;
import br.com.senai.sollaris.domain.resources.service.validations.UsuarioServiceValidation;
import lombok.RequiredArgsConstructor;

/*
 * ELE É A COZINHA
 * ELE QUE PREPARA
 * 
 * POST, PUT E DELETE (TCL) COMMIT (aprova), ROLLBACK (rejeita, desfaz) 
 * 
 * RESPONSE ENTITY: É O PADRÃO DO SPRING PARA DEVOLUÇÃO DAS RESPOSTAS
 * 
 */

@RequiredArgsConstructor
@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final UsuarioServiceValidation serviceValidation;
	
	public Page<ReturnUsuarioDto> listarUsuarios(Pageable page) {
		return usuarioRepository.findAll(page).map(ReturnUsuarioDto::new);
	}
		
	//Utilizado no EnderecoService e UsuarioService, também UsuarioController
	public Usuario listarUsuario(Long id) {
		return  usuarioRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Usuário não encontrado")); 
		
	}
	
	@Transactional
	public ResponseEntity<ReturnUsuarioDto> cadastrarUsuario(UsuarioDto usuarioDto, 
			UriComponentsBuilder uriBuilder) {
		serviceValidation.validarEmail(usuarioDto);
		serviceValidation.validarCPF(usuarioDto);
		
		Usuario usuario = new Usuario(usuarioDto);
		usuarioRepository.save(usuario);
		usuario.adicionarEndereco(usuario, usuarioDto.getEndereco());
		
		URI uri = uriBuilder.path("/api/users/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReturnUsuarioDto(usuario));

	}
	

	@Transactional
	public ResponseEntity<ReturnUsuarioPut> alterarUsuario(Long id, PutUsuarioDto usuarioDto) {
		serviceValidation.validarEmail(usuarioDto);
		Usuario usuario = listarUsuario(id);
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
	
	
	public ResponseEntity<ReturnUsuarioDto> logarUsuario(UsuarioLogin usuario) {
		return ResponseEntity.ok(usuarioRepository.login(usuario.getEmail(), usuario.getSenha())
				.map(ReturnUsuarioDto::new)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Email e/ou senha inválida, tente novamente!"))); 
	}

}
