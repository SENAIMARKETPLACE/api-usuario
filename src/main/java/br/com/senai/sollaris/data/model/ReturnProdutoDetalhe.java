package br.com.senai.sollaris.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReturnProdutoDetalhe {
	private Integer id;
	private String tamanho;
	private String peso;
	private String cor;
	private Long quantidade;
}
