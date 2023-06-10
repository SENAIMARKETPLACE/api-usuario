package br.com.senai.sollaris.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.senai.sollaris.domain.enums.Generos;
import br.com.senai.sollaris.domain.resources.dtos.input.EnderecoDto;
import br.com.senai.sollaris.domain.resources.dtos.input.PutUsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.input.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Usuario")
@Table(name = "usuarios")
public class Usuario {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	private LocalDate dt_nascimento;
	private LocalDateTime dt_registro;
	private LocalDateTime dt_alteracao;
	private String email;
	private String senha;
	private String telefone;
	
	@Enumerated(EnumType.STRING)
	private Generos genero;
	private String img;
	private String grupos_interesses;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Endereco> enderecos = new ArrayList<>();
	
	public Usuario(UsuarioDto usuarioDto) {
		this.nome = usuarioDto.getNome();
		this.cpf = usuarioDto.getCpf();
		this.dt_nascimento = usuarioDto.getDt_nascimento();
		this.dt_registro = LocalDateTime.now();
		this.email = usuarioDto.getEmail();
		this.senha = usuarioDto.getSenha();
		this.telefone = usuarioDto.getTelefone();
		this.genero = usuarioDto.getGenero();
		this.img = usuarioDto.getImg();
		this.grupos_interesses = formatarGrupoTexto(usuarioDto.getGrupos_interesses());	
		}

	//metodo usado para atualizar um Usuário, Json Manipulável
	public void atualizarInformacoes(Integer id, PutUsuarioDto usuarioDto) {
		this.id = id;
		
		if (usuarioDto.getNome() != null) {
			this.nome = usuarioDto.getNome();
			this.dt_alteracao = LocalDateTime.now();
		}
			
		if (usuarioDto.getEmail() != null) {
			this.email = usuarioDto.getEmail();
			this.dt_alteracao = LocalDateTime.now();
		}
			
		
		if (usuarioDto.getTelefone() != null) {
			this.telefone = usuarioDto.getTelefone();
			this.dt_alteracao = LocalDateTime.now();
		}
			
		
		if (usuarioDto.getImg() != null) {
			this.img = usuarioDto.getImg();
			this.dt_alteracao = LocalDateTime.now();
		}
		
		if (usuarioDto.getGenero() != null) {
			this.genero = usuarioDto.getGenero();
			this.dt_alteracao = LocalDateTime.now();
		}
		
		if (usuarioDto.getData_nascimento() != null) {
			this.dt_nascimento = usuarioDto.getData_nascimento();
			this.dt_alteracao = LocalDateTime.now();
		}
		
		if (usuarioDto.getCpf() != null) {
			this.cpf = usuarioDto.getCpf();
			this.dt_alteracao = LocalDateTime.now();
		}
		
		if (usuarioDto.getGrupo_de_interesses() != null) {
			String gruposInteresses = this.formatarGrupoTexto(usuarioDto.getGrupo_de_interesses());
			this.grupos_interesses = gruposInteresses;
			this.dt_alteracao = LocalDateTime.now();
		}
	}
	
	//Construtor usado no método validarEmail em UsuarioService
	public Usuario(PutUsuarioDto usuarioDto) {
		this.nome = usuarioDto.getNome();
		this.email = usuarioDto.getEmail();
		this.telefone = usuarioDto.getTelefone();
	}


	public void adicionarEndereco(Usuario usuario, EnderecoDto endereco) {
		this.enderecos.add(new Endereco(endereco, usuario));
		
	}
	
	//Ele formata o Array recebido de UsuarioDto para poder salvar no banco
	private String formatarGrupoTexto(String[] string) {
		String grupos_interesses = "";
		
		for (String categoria : string) {
			grupos_interesses += categoria;
			grupos_interesses += ",";
		}
		return grupos_interesses;
	}
	
	public String[] devolverGruposDeInteresse(String string) {
		String[] gruposInteresses = string.split(",");
		
		return gruposInteresses;
		
	}

}
