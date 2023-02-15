package br.com.senai.sollaris.domain.resources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.sollaris.domain.resources.service.ClienteService;

/*
 * ELE SÓ LEVA E DEVOLVE A REQUISICAO
 * GARÇOM VAI LEVAR PARA OS SERVIÇOS
 */

@RestController
@RequestMapping("api/users")
public class UsuarioController {
	
	@Autowired
	private ClienteService clienteService;
	
}
