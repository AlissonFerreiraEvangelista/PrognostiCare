package br.com.prognosticare.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import br.com.prognosticare.domain.entity.agenda.AgendaEntity;
import br.com.prognosticare.domain.entity.agenda.DtoDetalheAgenda;
import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;
import jakarta.persistence.QueryHint;

public interface AgendaRepository extends JpaRepository<AgendaEntity, UUID> {

    @Query(value = "FROM AgendaEntity a WHERE a.pessoa = :pessoa")
    List<DtoDetalheAgenda> findByAgendaEntityWherePessoaEntity(@Param("pessoa") PessoaEntity pessoa);


    @QueryHints({
        @QueryHint(
        name = "jakarta.persistence.lock.timeout", 
        value = "5000") 
        })
    @Query(nativeQuery = true, value = "SELECT * FROM tb_agenda a WHERE a.status_evento = 'ABERTO' ORDER BY a.data_agenda ASC FOR UPDATE SKIP LOCKED")
    List<AgendaEntity> findAllByStatusEventoAberto();
    
}
