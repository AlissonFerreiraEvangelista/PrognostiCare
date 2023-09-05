package br.com.prognosticare.domain.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.prognosticare.domain.entity.acompanhamento.AcompanhamentoEntity;
import br.com.prognosticare.domain.entity.acompanhamento.DtoDetalheAcompanhamento;
import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;

public interface AcompanhamentoRepository extends JpaRepository<AcompanhamentoEntity, UUID>{

    @Query(value = "FROM AcompanhamentoEntity a WHERE a.pessoa = :pessoa")
List<DtoDetalheAcompanhamento> findByAcompanhamentoEntityWherePessoaEntity(@Param("pessoa") PessoaEntity pessoa);

    
}
