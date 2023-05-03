package br.com.senai.sollaris.domain.resources.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.resources.dtos.input.PutUsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.input.UsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.input.UsuarioLogin;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioDto2;
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
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<Page<ReturnUsuarioDto>> listarTodosUsuarios (Pageable page) {
		return ResponseEntity.ok(usuarioService.listarUsuarios(page));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReturnUsuarioDto> listarUsuarios (@PathVariable Integer id) {
		return ResponseEntity.ok(new ReturnUsuarioDto(usuarioService.listarUsuario(id)));
	}
	
	//Utilizado na API de Compra para validar o Usuário e seu Endereço!
	@GetMapping("/validate")
	public ResponseEntity<ReturnUsuarioDto2> validarUsuario_Endereco(
			@RequestParam(name = "usuario", required = true) Integer usuarioId,
			@RequestParam(name = "endereco", required = true) Integer empresaId) {
		
		return usuarioService.buscarUsuario_Endereco(usuarioId, empresaId);
	}
	
	@PostMapping
	public ResponseEntity<ReturnUsuarioDto> cadastrarUsuario(@Valid @RequestBody UsuarioDto usuarioDto,
			UriComponentsBuilder uriBuilder){
		return usuarioService.cadastrarUsuario(usuarioDto, uriBuilder);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ReturnUsuarioDto> logarUsuario(@RequestBody UsuarioLogin usuario) {
		return usuarioService.logarUsuario(usuario);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ReturnUsuarioPut> alterarUsuario(@PathVariable Integer id, @RequestBody @Valid PutUsuarioDto usuarioDto) {
		return usuarioService.alterarUsuario(id, usuarioDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarUsuarios (@PathVariable Integer id){
		return usuarioService.deletarUsuario(id);
	}
	
}
