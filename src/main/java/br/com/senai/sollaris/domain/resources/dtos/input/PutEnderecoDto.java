package br.com.senai.sollaris.domain.resources.dtos.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutEnderecoDto {
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
