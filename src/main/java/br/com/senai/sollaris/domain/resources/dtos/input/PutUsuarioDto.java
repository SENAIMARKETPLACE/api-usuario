package br.com.senai.sollaris.domain.resources.dtos.input;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.senai.sollaris.domain.enums.Generos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutUsuarioDto {
	private Integer usuario_id;
	
	private String nome;
	
	@Email
	private String email;
	
	private String telefone;
	private String img;
	private String cpf;
	private Generos genero;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_nascimento;
	private String[] grupo_de_interesses;
}
