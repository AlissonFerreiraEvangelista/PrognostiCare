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
import br.com.prognosticare.domain.enums.Especialidade;
import br.com.prognosticare.domain.enums.TipoExame;
import jakarta.persistence.QueryHint;

public interface AgendaRepository extends JpaRepository<AgendaEntity, UUID> {

    @Query(value = "FROM AgendaEntity a WHERE a.pessoa = :pessoa")
    List<DtoDetalheAgenda> findByAgendaEntityWherePessoaEntity(@Param("pessoa") PessoaEntity pessoa);


    @Query(value = "FROM AgendaEntity  WHERE especialista =:especialista")
    List<DtoDetalheAgenda> findByAgendaEntityWhereEspecialista(@Param("especialista") Especialidade especialista);

    @Query(value = "FROM AgendaEntity  WHERE tipoExame =:tipoExame")
    List<DtoDetalheAgenda> findByAgendaEntityWhereTipoExame(@Param("tipoExame") TipoExame tipoExame);

    @QueryHints({
        @QueryHint(
        name = "jakarta.persistence.lock.timeout", 
        value = "5000") 
        })
    @Query(nativeQuery = true, value = "SELECT * FROM tb_agenda a WHERE a.status_evento = 'ABERTO' ORDER BY a.data_agenda ASC FOR UPDATE SKIP LOCKED")
    List<AgendaEntity> findAllByStatusEventoAberto();


    
    
}
