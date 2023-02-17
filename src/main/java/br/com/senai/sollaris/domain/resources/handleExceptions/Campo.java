package br.com.senai.sollaris.domain.resources.handleExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Campo {
	private String campo;
	private String mensagem;
}
