package br.com.senai.sollaris.domain.resources.dtos.output;

import java.time.LocalDateTime;
import java.util.List;

import br.com.senai.sollaris.domain.Generos;
import br.com.senai.sollaris.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor 
@NoArgsConstructor
public class ReturnUsuarioDto {
	private Long id;
	private LocalDateTime data_requisicao = LocalDateTime.now();
	private String nome;
	private Generos genero;
	private String img;
	private List<ReturnEnderecoDto> enderecos;
	
	public ReturnUsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.genero = usuario.getGenero();
		this.img = usuario.getImg();
		this.enderecos = usuario.getEnderecos().stream().map(endereco -> new ReturnEnderecoDto(endereco)).toList();
	}
}
	

