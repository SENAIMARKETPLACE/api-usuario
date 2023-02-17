ALTER TABLE enderecos 
	ADD constraint fk_endereco_usuario 
		FOREIGN KEY (usuario_id) 
			references usuarios(id);