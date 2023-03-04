package br.com.senai.sollaris.domain.resources.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.resources.dtos.input.EnderecoDto;
import br.com.senai.sollaris.domain.resources.service.EnderecoService;

@RestController
@RequestMapping("api/address")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@PostMapping
	public Object cadastrarEndereco(@RequestBody @Valid EnderecoDto endereco, 
			UriComponentsBuilder uriBuilder) {
		return enderecoService.cadastrarEndereco(endereco, uriBuilder);
	}
}
