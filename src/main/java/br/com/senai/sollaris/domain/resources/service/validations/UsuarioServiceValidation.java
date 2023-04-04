package br.com.senai.sollaris.domain.resources.service.validations;

import org.springframework.stereotype.Service;

import br.com.senai.sollaris.domain.Usuario;
import br.com.senai.sollaris.domain.repository.UsuarioRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.PutUsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.input.UsuarioDto;
import br.com.senai.sollaris.domain.resources.service.exceptions.CpfEmUsoException;
import br.com.senai.sollaris.domain.resources.service.exceptions.EmailEmUsoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioServiceValidation {
	
	private final UsuarioRepository usuarioRepository;
	
	public void validarEmail(UsuarioDto usuario) {
		boolean email_em_uso = usuarioRepository.findByEmail(usuario.getEmail())
		.stream()
		.anyMatch(usuarioSGDB -> !usuarioSGDB.equals(new Usuario(usuario)));
		
		if (email_em_uso)
			throw new EmailEmUsoException("Email em uso, tente novamente!");
	}
	
	public void validarEmail(PutUsuarioDto usuario) {
		boolean email_em_uso = usuarioRepository.findByEmail(usuario.getEmail())
				.stream()
				.anyMatch(usuarioSGDB -> !usuarioSGDB.equals(new Usuario(usuario)));
				
				if (email_em_uso)
					throw new EmailEmUsoException("Email em uso, tente novamente!");
		
	}
	
	public void validarCPF(UsuarioDto usuarioDto) {
		boolean cpf_em_uso = usuarioRepository.findByCpf(usuarioDto.getCpf())
				.stream()
				.anyMatch(usuarioSGDB -> !usuarioSGDB.equals(new Usuario(usuarioDto)));
		
		if (cpf_em_uso)
			throw new CpfEmUsoException("CPF em uso, tente novamente!");
				
	}
}
