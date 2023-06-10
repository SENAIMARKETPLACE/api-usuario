package br.com.senai.sollaris.domain.resources.dtos.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PutEnderecoUsuarioDto {
	
	@NotNull
	private Integer usuario_id;
	
	@NotNull
	private Integer endereco_id;
	
	@NotBlank
	@Size(min = 8, max = 8)
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
	@NotBlank
	private String complemento;
}
