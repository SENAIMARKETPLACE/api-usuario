package br.com.senai.sollaris.domain.resources.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.sollaris.domain.resources.dtos.input.PutEnderecoUsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioDto;
import br.com.senai.sollaris.domain.resources.service.UsuarioEnderecoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/users/address")
public class UsuarioEnderecoController {
	
	private final UsuarioEnderecoService enderecoService;
	
	@PutMapping
	public ResponseEntity<ReturnUsuarioDto> alterarEndereco(@RequestBody @Valid PutEnderecoUsuarioDto dto) {
		return enderecoService.alterarEndereco(dto);
	}
}
