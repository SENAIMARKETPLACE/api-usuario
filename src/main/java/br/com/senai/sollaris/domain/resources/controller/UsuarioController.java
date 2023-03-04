package br.com.senai.sollaris.domain.resources.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.resources.dtos.input.PutUsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.input.UsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioPut;
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
@CrossOrigin("http://localhost:3000")
public class UsuarioController {
	
	@Autowired
	private UsuarioService clienteService;
	
	@GetMapping
	public ResponseEntity<List<ReturnUsuarioDto>> listarUsuarios () {
		return ResponseEntity.ok(clienteService.listarUsuarios());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReturnUsuarioDto> listarUsuarios (@PathVariable Long id) {
		return ResponseEntity.ok(new ReturnUsuarioDto(clienteService.listarUsuario(id)));
	}
	
	@PostMapping
	public ResponseEntity<ReturnUsuarioDto> cadastrarUsuario(@Valid @RequestBody UsuarioDto usuarioDto,
			UriComponentsBuilder uriBuilder){
		return clienteService.cadastrarUsuario(usuarioDto, uriBuilder);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ReturnUsuarioPut> alterarUsuario(@PathVariable Long id, @RequestBody @Valid PutUsuarioDto usuarioDto) {
		return clienteService.alterarUsuario(id, usuarioDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarUsuarios (@PathVariable Long id){
		return clienteService.deletarUsuario(id);
	}
	
}
