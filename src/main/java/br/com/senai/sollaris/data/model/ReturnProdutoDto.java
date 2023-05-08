package br.com.senai.sollaris.data.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReturnProdutoDto {
	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
	private String img;
	private String publico;
	private ReturnProdutoCategoria categoria; 
	private List<ReturnProdutoDetalhe> detalhes_dos_produtos;
	private ReturnProdutoDetalhe detalhes_do_produto;
}
