package br.com.senai.sollaris.domain.resources.handleExceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.senai.sollaris.domain.Usuario;
import br.com.senai.sollaris.domain.infra.UsuarioConfig;
import br.com.senai.sollaris.domain.resources.service.exceptions.EmailEmUsoException;
import br.com.senai.sollaris.domain.resources.service.exceptions.ObjetoNaoEncontradoException;

/*
 * ControllerAdvice = Fala para o Spring que essa clase ela trata os erros
 * ResponseEntityException = É O PADRÃO DE RESPOSTA PARA COISAS NEGATIVAS (ERROS)
 */

@ControllerAdvice
public class handleExceptions extends ResponseEntityExceptionHandler{
	
	
	//Ele pega as mensagem dos erros
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		//Lista para guardar os campos e erros
		List<Campo> listaDeErros = new ArrayList<>();
		
		//Uma lista contendo os campos e mensagem dos erros
		List<FieldError> todosOsErros = ex.getBindingResult().getFieldErrors();
		
		
		todosOsErros.forEach(field_error -> {
			String nome = field_error.getField();
			String mensagem = messageSource.getMessage(field_error, LocaleContextHolder.getLocale());
			
			listaDeErros.add(new Campo(nome, mensagem));
		});
		
		RespostaException respostaException = new RespostaException(status, listaDeErros);
		
		return handleExceptionInternal(ex, respostaException, headers, status, request);
	}
	
	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<Object> ObjetoNaoEncontrado(ObjetoNaoEncontradoException ex, 
			HttpServletRequest requestPath, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		RespostaException resposta = new RespostaException(ex, status, requestPath);
		
		return handleExceptionInternal(ex, resposta, new HttpHeaders(), status, request);
		
		
	}
	
	@ExceptionHandler(EmailEmUsoException.class)
	public ResponseEntity<Object> ObjetoNaoEncontrado(EmailEmUsoException ex, 
			HttpServletRequest requestPath, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		RespostaException resposta = new RespostaException(ex, status, requestPath);
		
		return handleExceptionInternal(ex, resposta, new HttpHeaders(), status, request);
		
		
	}
}
