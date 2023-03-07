package br.com.senai.sollaris.domain.resources.dtos.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.senai.sollaris.domain.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ReturnEnderecoDto {
	private Long id;
	private String cep;
	private String logradouro;
	private String numero;
	private String estado;
	private String bairro;
	private String cidade;
	private String complemento;
	
	ReturnEnderecoDto(Endereco endereco){
		this.id = endereco.getId();
		this.cep = endereco.getCep();
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.estado = endereco.getEstado();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
		this.complemento = endereco.getComplemento();
	}
}
