package br.com.senai.sollaris.domain.resources.dtos.input;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * JAVAX.VALIDATION.CONSTRAINT
 * notblank = String: não pode ser vazia e nem null
 * valid: ele fala para o bean validation VALIDAR
 * 
 * DTO: FLEXIBILIDADE DE INSERIR E DEVOLVER
 */

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
	@NotBlank
	private String nome;
	@NotBlank
	private String cpf;
	@NotNull
	private LocalDate dt_nascimento;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String senha;
	@NotBlank
	private String telefone;
	@NotNull
	@Valid
	private EnderecoDto endereco;
}
