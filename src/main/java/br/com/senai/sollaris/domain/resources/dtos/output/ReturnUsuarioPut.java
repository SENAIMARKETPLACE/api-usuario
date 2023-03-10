package br.com.senai.sollaris.domain.resources.dtos.output;

import br.com.senai.sollaris.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnUsuarioPut {
	
	private String nome;
	private String email;
	private String senha;
	private String telefone;
	
	public ReturnUsuarioPut(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.telefone = usuario.getSenha();
	}

}
