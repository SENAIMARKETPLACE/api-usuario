package br.com.senai.sollaris.domain.resources.dtos.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Size: ele define o tamanho que deve ter o dado
 */

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto {
	
	@NotNull
	@Valid
	private UsuarioDtoId usuario;
	
	@NotBlank
	@Size(min = 8 ,max = 8)
	private String cep;
	@NotBlank
	private String logradouro;
	@NotBlank
	private String numero;
	@NotBlank
	private String estado;
	@NotBlank
	private String bairro;
	@NotBlank
	private String cidade;
	
	private String complemento;
}
