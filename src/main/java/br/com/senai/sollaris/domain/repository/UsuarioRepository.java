package br.com.senai.sollaris.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.senai.sollaris.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findByCpf(String cpf);
	
	Optional<Usuario> findBySenha(String senha_antiga);
	
	@Query("SELECT u from Usuario u WHERE u.email = :emailUser and u.senha = :senhaUser")
	Optional<Usuario> login(@Param("emailUser") String emailUser, @Param("senhaUser") String senhaUser);
	
	@Query("SELECT u from Usuario u INNER JOIN u.enderecos e WHERE u.id = :usuario_id AND e.id = :empresa_id")
	Optional<Usuario> buscarUsuario_Endereco(@Param("usuario_id") Integer usuarioId, 
			@Param("empresa_id") Integer empresaId);

	

}
