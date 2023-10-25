package br.com.prognosticare.domain.entity.pessoa;

import java.util.UUID;

public interface DtoProfileProjection {
    UUID getPessoaId();
    String getNome();
    Boolean getAtivo();
    Boolean getTipoResponsavel();
}

