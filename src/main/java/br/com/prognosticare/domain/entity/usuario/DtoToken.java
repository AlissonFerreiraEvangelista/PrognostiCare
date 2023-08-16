package br.com.prognosticare.domain.entity.usuario;

import java.util.UUID;

import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;

public record DtoToken(
    String token,
    UUID pessoaEntity
) {
    
}
