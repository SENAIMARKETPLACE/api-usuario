package br.com.senai.sollaris.domain.resources.dtos.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.senai.sollaris.domain.Usuario;
import br.com.senai.sollaris.domain.enums.Generos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnUsuarioPut {
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime data_requisicao = LocalDateTime.now();
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_nascimento;
	private Generos genero;
	private String img;
	private String[] gruposDeInteresse;

	public ReturnUsuarioPut(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.telefone = usuario.getTelefone();
		this.img = usuario.getImg();
		this.cpf = usuario.getCpf();
		this.data_nascimento = usuario.getDt_nascimento();
		this.genero = usuario.getGenero();
		this.gruposDeInteresse = usuario.devolverGruposDeInteresse(usuario.getGrupos_interesses());

	}

}
