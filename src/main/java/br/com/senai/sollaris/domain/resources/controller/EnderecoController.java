package br.com.senai.sollaris.domain.resources.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.sollaris.domain.resources.dtos.input.PutEnderecoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnEnderecoDto;
import br.com.senai.sollaris.domain.resources.service.EnderecoService;

@RestController
@RequestMapping("api/user/address")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping
	public ResponseEntity<List<ReturnEnderecoDto>> listarEnderecos(){
		return enderecoService.listarEnderecos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReturnEnderecoDto> listarEndereco(@PathVariable Long id) {
		return enderecoService.listarEndereco(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ReturnEnderecoDto> alterarEndereco(@PathVariable Long id, @RequestBody PutEnderecoDto endereco) {
		return enderecoService.alterarEndereco(id, endereco);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarEndereco(@PathVariable Long id) {
		return enderecoService.deletarEndereco(id);
	}
}
