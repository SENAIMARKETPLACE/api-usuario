package br.com.senai.sollaris.domain.resources.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.data.ProdutoFeign;
import br.com.senai.sollaris.data.model.ReturnProdutoDto;
import br.com.senai.sollaris.domain.Usuario;
import br.com.senai.sollaris.domain.repository.UsuarioRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.PutUsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.input.UsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.input.UsuarioLogin;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioDto2;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioLogin;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnUsuarioPut;
import br.com.senai.sollaris.domain.resources.service.exceptions.CategoriaNaoEncontradoException;
import br.com.senai.sollaris.domain.resources.service.exceptions.ConsumoDeApiException;
import br.com.senai.sollaris.domain.resources.service.exceptions.ObjetoNaoEncontradoException;
import br.com.senai.sollaris.domain.resources.service.exceptions.Usuario_EnderecoNaoEncontradoException;
import br.com.senai.sollaris.domain.resources.service.validations.UsuarioServiceValidation;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * ELE É A COZINHA
 * ELE QUE PREPARA
 * 
 * POST, PUT E DELETE (TCL) COMMIT (aprova), ROLLBACK (rejeita, desfaz) 
 * 
 * RESPONSE ENTITY: É O PADRÃO DO SPRING PARA DEVOLUÇÃO DAS RESPOSTAS
 * 
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class UsuarioService {
	private final Environment env;
	
	private final UsuarioRepository usuarioRepository;
	private final UsuarioServiceValidation serviceValidation;
	private final ProdutoFeign produtoFeign;
	
	public Page<ReturnUsuarioDto> listarUsuarios(Pageable page) {
		return usuarioRepository.findAll(page).map(ReturnUsuarioDto::new);
	}
		
	public Usuario listarUsuario(Integer id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Usuário não encontrado")); 
		
	}
	
	//Utilizado em outra API!
	public ResponseEntity<ReturnUsuarioDto2> buscarUsuario_Endereco(Integer usuarioId, Integer empresaId) {
		return ResponseEntity.ok(usuarioRepository.buscarUsuario_Endereco(usuarioId, empresaId)
				.map(ReturnUsuarioDto2::new)
				.orElseThrow(() -> new Usuario_EnderecoNaoEncontradoException("Usuário e/ou endereço não encontrado, tente novamente")));
		
	}
	
	@Transactional
	public ResponseEntity<ReturnUsuarioDto> cadastrarUsuario(UsuarioDto usuarioDto, 
			UriComponentsBuilder uriBuilder) {
		serviceValidation.validarEmail(usuarioDto);
		serviceValidation.validarCPF(usuarioDto);
		
		Usuario usuario = new Usuario(usuarioDto);
		usuarioRepository.save(usuario);
		usuario.adicionarEndereco(usuario, usuarioDto.getEndereco());
		
		URI uri = uriBuilder.path("/api/users/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReturnUsuarioDto(usuario));

	}
	

	@Transactional
	public ResponseEntity<ReturnUsuarioPut> alterarUsuario(Integer id, PutUsuarioDto usuarioDto) {
		serviceValidation.validarEmail(usuarioDto);
		Usuario usuario = listarUsuario(id);

		usuario.atualizarInformacoes(id, usuarioDto);
		
		return ResponseEntity.ok(new ReturnUsuarioPut(usuario));
	}
	
	@Transactional
	public ResponseEntity<Object> deletarUsuario(Integer id) {
		if(usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
			// eu deletei com sucesso mas não tenho nada para te devolver
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	public ResponseEntity<ReturnUsuarioLogin> logarUsuario(UsuarioLogin usuarioLogin, Pageable pageable) {
		//Busca e Validação de Login
		ReturnUsuarioDto usuario = usuarioRepository.login(usuarioLogin.getEmail(), usuarioLogin.getSenha())
			.map(ReturnUsuarioDto::new)
			.orElseThrow(() -> new ObjetoNaoEncontradoException("Email e/ou senha inválida, tente novamente!"));
		
		List<ReturnProdutoDto> produtos = new ArrayList<>();
		
		try {
			log.info("USUARIO_SERVICE :::: Get Request On" + env.getProperty("local.server.port") + " port");
			
			for (int i = 0; i < usuario.getGruposDeInteresse().length; i++) {
				Integer[] gruposDeInteresse = usuario.getGruposDeInteresse();
				int categoria_id = gruposDeInteresse[i]; 
				List<ReturnProdutoDto> produtoDto = produtoFeign.listarProdutoPorCategoria(categoria_id, pageable).getBody();
				
				if (produtoDto.isEmpty()) {
					throw new CategoriaNaoEncontradoException("Essa categoria não existe ou não há nenhum produto vinculado à esta categoria");
				}
				
				produtoDto.forEach(produto -> produtos.add(produto));
			}
			
			return ResponseEntity.ok(new ReturnUsuarioLogin(usuario, produtos));
			
		} catch (FeignException e) {
			throw new ConsumoDeApiException("Não há nenhum produto relacionado ao grupo de interesse do usuário");
		}
		
		
	}


}
