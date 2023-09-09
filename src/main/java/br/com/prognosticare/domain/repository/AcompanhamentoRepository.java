package br.com.prognosticare.domain.repository;

import java.util.List;
import java.util.UUID;

import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import br.com.prognosticare.domain.entity.acompanhamento.AcompanhamentoEntity;
import br.com.prognosticare.domain.entity.acompanhamento.DtoDetalheAcompanhamento;
import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;
import br.com.prognosticare.domain.enums.Status;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;

public interface AcompanhamentoRepository extends JpaRepository<AcompanhamentoEntity, UUID> {

    @Query(value = "FROM AcompanhamentoEntity a WHERE a.pessoa = :pessoa")
    List<DtoDetalheAcompanhamento> findByAcompanhamentoEntityWherePessoaEntity(@Param("pessoa") PessoaEntity pessoa);

    
      @QueryHints({
        @QueryHint(
        name = "javax.persistence.lock.timeout", 
        value = "5000") 
        })
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(nativeQuery = true, value = "SELECT * FROM tb_acompanhamento a WHERE a.status_evento = 'ABERTO' ORDER BY a.data_acompanhamento ASC FOR UPDATE SKIP LOCKED")
    List<AcompanhamentoEntity> findAllByStatusEvento();
     
    

     /*
      *
       @QueryHints({
        @QueryHint(
            name = "javax.persistence.lock.timeout", 
            value = "5000" 
        )
    })
    @Query("SELECT a FROM AcompanhamentoEntity a WHERE a.statusEvento = :statusEvento")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<AcompanhamentoEntity> findAllByStatusEvento(
        @Param("statusEvento") Status statusEvento
    );
    
      */

     /*
        @QueryHints({
            @QueryHint(
                name = "jakarta.persistence.lock.timeout", 
                value = LockOptions.SKIP_LOCKED + ""
            )
        })
        @Query("SELECT a FROM AcompanhamentoEntity a WHERE a.statusEvento = :statusEvento")
        @Lock(LockModeType.PESSIMISTIC_WRITE)
        List<AcompanhamentoEntity> findAllByStatusEvento(
            @Param("statusEvento") Status statusEvento
        );
     
     */ 
   
    
}
