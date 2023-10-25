package br.com.prognosticare.domain.repository;



import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.prognosticare.domain.entity.pessoa.DtoProfile;
import br.com.prognosticare.domain.entity.pessoa.DtoProfileProjection;
import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;


public interface PessoaRepository extends JpaRepository<PessoaEntity, UUID>{


    @Query(value = "FROM PessoaEntity p WHERE p.pessoaId = ?1")
    PessoaEntity pesquisaPorIdUsuario(UUID pessoaId);


    @Query(value="FROM PessoaEntity p WHERE p.email = ?1")
    PessoaEntity findByEmail(String email);

    // @Query("SELECT p.pessoaId, p.nome, p.ativo, p.tipoResponsavel FROM PessoaEntity p WHERE p.responsavel.pessoaId = :responsavelId")
    // List<DtoProfile> findByDependente(@Param("responsavelId") UUID responsavelId);

    @Query("SELECT p.pessoaId as pessoaId, p.nome as nome, p.ativo as ativo, p.tipoResponsavel as tipoResponsavel FROM PessoaEntity p WHERE p.responsavel.pessoaId = :responsavelId")
    List<DtoProfileProjection> findByDependente(@Param("responsavelId") UUID responsavelId);






           
    
    
}
