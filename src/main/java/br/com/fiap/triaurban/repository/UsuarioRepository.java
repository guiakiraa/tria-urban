package br.com.fiap.triaurban.repository;

import br.com.fiap.triaurban.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM usuario WHERE username = :username", nativeQuery = true)
    Optional<Usuario> findByUsername(String username);
}
