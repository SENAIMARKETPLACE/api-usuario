ALTER TABLE enderecos 
	ADD constraint fk_endereco_usuario 
		FOREIGN KEY (idUsuario) 
			references usuarios(id);