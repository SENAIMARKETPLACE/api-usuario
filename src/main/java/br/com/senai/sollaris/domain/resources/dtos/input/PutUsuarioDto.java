package br.com.senai.sollaris.domain.resources.dtos.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutUsuarioDto {
	
	private String nome;
	
	@Email
	private String email;
	private String senha;
	
	@Size(min = 11, max = 11)
	private String telefone;
	private String img;
}
