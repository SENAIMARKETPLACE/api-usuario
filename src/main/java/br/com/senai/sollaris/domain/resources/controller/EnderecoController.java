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

import br.com.senai.sollaris.domain.resources.dtos.input.EnderecoDto;
import br.com.senai.sollaris.domain.resources.dtos.input.PutEnderecoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnEnderecoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioDto;
import br.com.senai.sollaris.domain.resources.service.EnderecoService;

@RestController
@RequestMapping("api/user/address")
@CrossOrigin("http://localhost:3000")
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

	@PostMapping
	public ResponseEntity<ReturnUsuarioDto> cadastrarEndereco(@RequestBody @Valid EnderecoDto endereco,
			UriComponentsBuilder uriBuilder) {
		return enderecoService.cadastrarEndereco(endereco, uriBuilder);

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
