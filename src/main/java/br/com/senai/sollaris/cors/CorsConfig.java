package br.com.senai.sollaris.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") //mapeamento global
                .allowedOrigins("http://localhost:3000") //  permite solicitações apenas do domínio http://localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*") //  permite todos os cabeçalhos da solicitação
                .allowCredentials(true) // permite o compartilhamento de cookies de autenticação
                .maxAge(3600); // define o tempo de cache máximo para a resposta de preflight em segundos
			}
		};
	}
}
