package br.com.prognosticare.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;

public interface PessoaRepository extends JpaRepository<PessoaEntity, UUID>{
    
}
