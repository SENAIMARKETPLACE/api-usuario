package br.com.senai.sollaris.domain.resources.service;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.Endereco;
import br.com.senai.sollaris.domain.Usuario;
import br.com.senai.sollaris.domain.repository.EnderecoRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.EnderecoDto;
import br.com.senai.sollaris.domain.resources.dtos.input.PutEnderecoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnEnderecoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioDto;
import br.com.senai.sollaris.domain.resources.service.exceptions.ObjetoNaoEncontradoException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoService {

	private final UsuarioService usuarioService;
	private final EnderecoRepository enderecoRepository;

	public ResponseEntity<List<ReturnEnderecoDto>> listarEnderecos() {
		return ResponseEntity.ok(enderecoRepository.findAll()
				.stream().map(ReturnEnderecoDto::new).toList());
	}
	
	public ResponseEntity<ReturnEnderecoDto> listarEndereco(Long id) {
		return ResponseEntity.ok(
				new ReturnEnderecoDto(enderecoRepository.findById(id).
						orElseThrow(() -> new ObjetoNaoEncontradoException("Endereço não encontrado"))));
		
	}

	@Transactional
	public ResponseEntity<ReturnUsuarioDto> cadastrarEndereco(EnderecoDto endereco, UriComponentsBuilder uriBuilder) {
		// Estou validando o id inserido no Usuario se existe
		Usuario usuario = usuarioService.listarUsuario(endereco.getUsuario_id());
		usuario.adicionarEndereco(usuario, endereco);

		URI uri = uriBuilder.path("api/address/{id}").buildAndExpand(usuario.getId()).toUri();
		ReturnUsuarioDto usuarioDto = new ReturnUsuarioDto(usuario);

		return ResponseEntity.created(uri).body(usuarioDto);
	}

	@Transactional
	public ResponseEntity<ReturnEnderecoDto> alterarEndereco(Long id, PutEnderecoDto enderecoAlterar) {
		Endereco endereco = enderecoRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Endereço não localizado"));
		
		endereco.alterar(enderecoAlterar);
		
		return ResponseEntity.ok(new ReturnEnderecoDto(endereco));
	}
	
	@Transactional
	public ResponseEntity<Object> deletarEndereco(Long id) {
		if (enderecoRepository.existsById(id)) {
			enderecoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}


}
