package br.com.senai.sollaris.domain.resources.handleExceptions;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.senai.sollaris.domain.resources.service.exceptions.EmailEmUsoException;
import br.com.senai.sollaris.domain.resources.service.exceptions.ObjetoNaoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(value = Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RespostaException {

	private String titulo;
	private StringBuffer Url;
	private Integer status;
	private String recurso;
	private LocalDateTime dataRequisicao;
	private List<Campo> campos;
	
	public RespostaException(ObjetoNaoEncontradoException ex, HttpStatus status, HttpServletRequest requestPath) {
		this.titulo = ex.getMessage();
		this.status = status.value();
		this.recurso = requestPath.getRequestURI();
		this.Url = requestPath.getRequestURL();
		this.dataRequisicao = LocalDateTime.now();
	}

	public RespostaException(HttpStatus status, List<Campo> campos) {
		this.titulo = "Campos inválidos, tente novamente";
		this.status = status.value();
		this.dataRequisicao = LocalDateTime.now();
		this.campos = campos;
	}

	public RespostaException(EmailEmUsoException ex, HttpStatus status, HttpServletRequest requestPath) {
		this.titulo = ex.getMessage();
		this.status = status.value();
		this.recurso = requestPath.getRequestURI();
		this.Url = requestPath.getRequestURL();
		this.dataRequisicao = LocalDateTime.now();
	}
	
	
}
