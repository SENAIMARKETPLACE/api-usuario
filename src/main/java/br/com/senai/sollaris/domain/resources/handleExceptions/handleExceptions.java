package br.com.senai.sollaris.domain.resources.handleExceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.senai.sollaris.domain.resources.service.exceptions.ObjetoNaoEncontradoException;

@ControllerAdvice
public class handleExceptions extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<Object> ObjetoNaoEncontrado(ObjetoNaoEncontradoException ex, 
			HttpServletRequest requestPath, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		RespostaException resposta = new RespostaException(ex, status, requestPath);
		
		return handleExceptionInternal(ex, resposta, new HttpHeaders(), status, request);
		
		
	}
}
