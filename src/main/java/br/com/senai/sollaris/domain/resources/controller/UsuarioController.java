package br.com.senai.sollaris.domain.resources.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.Usuario;
import br.com.senai.sollaris.domain.resources.dtos.input.UsuarioDto;
import br.com.senai.sollaris.domain.resources.service.UsuarioService;

/*
 * ELE SÓ LEVA E DEVOLVE A REQUISICAO
 * GARÇOM VAI LEVAR PARA OS SERVIÇOS
 * 
 * VALID: ELE VALIDA PELO BEAN VALIDATION
 * REQUESTBODY: ELE FALA PARA O SPRING QUE VAI TER DADOS NO CORPO
 */

@RestController
@RequestMapping("api/users")
public class UsuarioController {
	
	@Autowired
	private UsuarioService clienteService;
	
	@GetMapping
	public List<Usuario> listarUsuarios () {
		return clienteService.listarUsuarios();
	}
	
	@GetMapping("/{id}")
	public List<Usuario> listarUsuarios (@PathVariable Long id) {
		return clienteService.listarUsuarios();
	}
	
	@PostMapping
	public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody UsuarioDto usuarioDto,
			UriComponentsBuilder uriBuilder) {
		return clienteService.cadastrarUsuario(usuarioDto, uriBuilder);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarUsuarios (@PathVariable Long id){
		return clienteService.deletarUsuario(id);
	}
	
}
