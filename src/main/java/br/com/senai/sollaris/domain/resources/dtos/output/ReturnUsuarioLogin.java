package br.com.senai.sollaris.domain.resources.dtos.output;

import java.util.List;

import br.com.senai.sollaris.data.model.ReturnProdutoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReturnUsuarioLogin {
	private ReturnUsuarioDto usuario;
	private List<ReturnProdutoDto> produtos;
}
