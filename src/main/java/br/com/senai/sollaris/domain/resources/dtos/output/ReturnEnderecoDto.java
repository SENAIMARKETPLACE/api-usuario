package br.com.senai.sollaris.domain.resources.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnEnderecoDto {
	private Long id;
	private ReturnUsuarioDto usuario;
}
