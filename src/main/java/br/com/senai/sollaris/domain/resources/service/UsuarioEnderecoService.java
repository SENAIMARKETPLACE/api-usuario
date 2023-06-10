package br.com.senai.sollaris.domain.resources.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.senai.sollaris.domain.Endereco;
import br.com.senai.sollaris.domain.Usuario;
import br.com.senai.sollaris.domain.repository.EnderecoRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.PutEnderecoDto;
import br.com.senai.sollaris.domain.resources.dtos.input.PutEnderecoUsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioEnderecoService {
	
	private final UsuarioService usuarioService;
	private final EnderecoRepository enderecoRepository;

	public ResponseEntity<ReturnUsuarioDto> alterarEndereco(PutEnderecoUsuarioDto dto) {
		Usuario usuario = usuarioService.listarUsuario(dto.getUsuario_id());
		boolean isAlterado = false;
		
		for (Endereco endereco : usuario.getEnderecos()) {
		    if (endereco.getId() == dto.getEndereco_id()) {
		        endereco.alterar(dto, usuario);
		        enderecoRepository.save(endereco);
		        isAlterado = true;
		        break; // Encerra o loop, pois o endereço foi encontrado
		    }
		}
		
		if (isAlterado)
			return ResponseEntity.ok(new ReturnUsuarioDto(usuario));
		
		return ResponseEntity.notFound().build();
		
	}
	
}
