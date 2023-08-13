package br.com.prognosticare.domain.entity.pessoa;

import java.util.UUID;

import br.com.prognosticare.domain.entity.usuario.DtoAutenticacao;
import jakarta.validation.constraints.NotNull;

public record DtoAtualizaPessoa(
    @NotNull
    UUID pessoa_id,
    String nome,
    String cpf,
    String dataNascimento,
    TipoSanguineo tipoSanguineo,
    Boolean alergia,
    Boolean tipoResponsavel,
    String cartaoNacional,
    String cartaoPlanoSaude,
    DtoAutenticacao usuario

) {
    
}
