package br.com.senai.sollaris.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReturnProdutoCategoria {
	private Integer id;
	private String nome;
	private ReturnSubCategoriaDto sub_categoria;
}
