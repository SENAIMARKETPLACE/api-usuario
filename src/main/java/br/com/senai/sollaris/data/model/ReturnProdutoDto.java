package br.com.senai.sollaris.data.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter
@JsonInclude(value = Include.NON_NULL)
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
