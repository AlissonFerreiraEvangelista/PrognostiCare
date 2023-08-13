package br.com.prognosticare.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.prognosticare.domain.entity.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    
    @Query(value="select u from Usuario u where u.email = ?1")
    Usuario findUsuarioByEmail(String email);

    @Query(value="select u from Usuario u where u.email = ?1")
    Optional<Usuario> findByEmail(String email);
}
