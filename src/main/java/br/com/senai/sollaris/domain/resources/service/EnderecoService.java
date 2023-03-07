package br.com.senai.sollaris.domain.resources.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.Usuario;
import br.com.senai.sollaris.domain.repository.EnderecoRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.EnderecoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioDto;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class EnderecoService {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Transactional
	public ResponseEntity<ReturnUsuarioDto> cadastrarEndereco(EnderecoDto endereco, 
			UriComponentsBuilder uriBuilder) {
		//Estou validando o id inserido no Usuario se existe
		Usuario usuario = usuarioService.listarUsuario(endereco.getUsuario().getId());
		usuario.adicionarEndereco(endereco);
		
		
		URI uri = uriBuilder.path("api/address/{id}").buildAndExpand(usuario.getId()).toUri();
		ReturnUsuarioDto usuarioDto = new ReturnUsuarioDto(usuario);
		
		return ResponseEntity.created(uri).body(usuarioDto);
	}
}
