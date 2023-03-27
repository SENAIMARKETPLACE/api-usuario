package br.com.senai.sollaris.domain.resources.dtos.input;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Data
public class UsuarioLogin {
	
	@NotBlank
	private String email;
	@NotBlank
	private String senha;
}
