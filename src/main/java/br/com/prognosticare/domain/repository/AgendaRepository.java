package br.com.prognosticare.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.prognosticare.domain.entity.agenda.AgendaEntity;
import br.com.prognosticare.domain.entity.agenda.DtoDetalheAgenda;
import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;

public interface AgendaRepository extends JpaRepository<AgendaEntity, UUID> {

    @Query(value = "FROM AgendaEntity a WHERE a.pessoa = :pessoa")
    List<DtoDetalheAgenda> findByAgendaEntityWherePessoaEntity(@Param("pessoa") PessoaEntity pessoa);
    
}
