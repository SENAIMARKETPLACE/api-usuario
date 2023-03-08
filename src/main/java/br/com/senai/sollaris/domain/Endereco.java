package br.com.senai.sollaris.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.senai.sollaris.domain.resources.dtos.input.EnderecoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enderecos")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(foreignKeyDefinition = "fk_endereco_usuario", name = "usuario_id"))
	private Usuario usuario;

	private String cep;
	private String logradouro;
	private String numero;
	private String estado;
	private String bairro;
	private String cidade;
	private String complemento;

	public Endereco(EnderecoDto enderecoDto, Usuario usuario) {
		this.usuario = usuario;
		this.cep = enderecoDto.getCep();
		this.logradouro = enderecoDto.getLogradouro();
		this.numero = enderecoDto.getNumero();
		this.estado = enderecoDto.getEstado();
		this.bairro = enderecoDto.getEstado();
		this.cidade = enderecoDto.getCidade();
		this.complemento = enderecoDto.getComplemento();
	}
}
