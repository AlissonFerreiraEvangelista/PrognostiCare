package br.com.prognosticare.domain.repository;



import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.prognosticare.domain.entity.pessoa.DtoDetalheDependente;
import br.com.prognosticare.domain.entity.pessoa.DtoDetalhePessoa;
import br.com.prognosticare.domain.entity.pessoa.DtoPessoaComDependenteDto;
import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;


public interface PessoaRepository extends JpaRepository<PessoaEntity, UUID>{


    @Query(value = "FROM PessoaEntity p WHERE p.pessoa_id = ?1")
    PessoaEntity pesquisaPorIdUsuario(UUID pessoa_id);


    @Query(value="FROM PessoaEntity p WHERE p.email = ?1")
    PessoaEntity findByEmail(String email);

    @Query("SELECT NEW br.com.prognosticare.domain.entity.pessoa.DtoPessoaComDependenteDto(p, d) FROM PessoaEntity p LEFT JOIN p.dependente d WHERE p.pessoa_id = :responsavelId")
    List<DtoPessoaComDependenteDto> findResponsavelComDependentes(@Param("responsavelId") UUID responsavelId);

    

            
    
    
}
