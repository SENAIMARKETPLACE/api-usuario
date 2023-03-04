package br.com.senai.sollaris;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API do Usuário",
		description = "Realiza as operações CRUD em Usuário, com o objetivo de manter usuário",
		version = "2.7.8",
		license = @License(name = "Pertencente a: Sollaris Marketplace", url = "https://github.com/SENAIMARKETPLACE")))

public class SollarisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SollarisApplication.class, args);
	}

}
