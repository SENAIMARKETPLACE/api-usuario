package br.com.senai.sollaris.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.senai.sollaris.domain.resources.dtos.input.PutUsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.input.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	
	private LocalDate dt_nascimento;
	private String email;
	private String senha;
	private String telefone;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();
	
	public Usuario(UsuarioDto usuarioDto) {
		this.nome = usuarioDto.getNome();
		this.cpf = usuarioDto.getCpf();
		this.dt_nascimento = usuarioDto.getDt_nascimento();
		this.email = usuarioDto.getEmail();
		this.senha = usuarioDto.getSenha();
		this.telefone = usuarioDto.getTelefone();
		this.enderecos.add(new Endereco(usuarioDto.getEndereco()));
	}

	public void atualizarInformacoes(Long id, PutUsuarioDto usuarioDto) {
		this.id = id;
		this.nome = usuarioDto.getNome();
		this.email = usuarioDto.getEmail();
		this.senha = usuarioDto.getSenha();
		this.telefone = usuarioDto.getTelefone();
		
	}
}
