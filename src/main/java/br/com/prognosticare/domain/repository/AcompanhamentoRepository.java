package br.com.prognosticare.domain.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prognosticare.domain.entity.acompanhamento.AcompanhamentoEntity;

public interface AcompanhamentoRepository extends JpaRepository<AcompanhamentoEntity, UUID>{
    
}
