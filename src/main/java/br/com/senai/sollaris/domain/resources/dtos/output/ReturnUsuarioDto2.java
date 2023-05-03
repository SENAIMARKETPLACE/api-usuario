package br.com.senai.sollaris.domain.resources.dtos.output;

import java.time.LocalDateTime;

import br.com.senai.sollaris.domain.Usuario;
import br.com.senai.sollaris.domain.enums.Generos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReturnUsuarioDto2 {
	private Integer id;
	private LocalDateTime data_requisicao = LocalDateTime.now();
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private Generos genero;
	private String img;
	private ReturnEnderecoDto endereco;
	
	public ReturnUsuarioDto2(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.genero = usuario.getGenero();
		this.img = usuario.getImg();
		this.cpf = usuario.getCpf();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.endereco = usuario.getEnderecos()
				.stream().map(endereco -> new ReturnEnderecoDto(endereco))
					.findFirst().orElseThrow();
	}
}
