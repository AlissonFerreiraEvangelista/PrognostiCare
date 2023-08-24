package br.com.prognosticare.domain.entity.dto;

import java.util.UUID;

import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;

public record DtoToken(
    String token,
    UUID pessoaEntity
) {
    
}
