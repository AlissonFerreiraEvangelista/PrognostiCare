package br.com.prognosticare.domain.entity.pessoa;

import java.util.UUID;

public record DtoProfile(
    UUID pessoaId,
    String nome,
    Boolean ativo,
    Boolean tipoResponsavel

) {
    
}
