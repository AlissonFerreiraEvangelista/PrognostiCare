package br.com.prognosticare.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;

public interface PessoaRepository extends JpaRepository<PessoaEntity, UUID>{


    @Query(value = "FROM PessoaEntity p WHERE p.usuario.id = ?1")
    PessoaEntity pesquisaPorIdUsuario(UUID user_id);
    
}
