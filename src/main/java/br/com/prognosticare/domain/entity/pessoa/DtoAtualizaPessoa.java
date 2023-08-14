package br.com.prognosticare.domain.entity.pessoa;


import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DtoAtualizaPessoa(
    @NotNull
    UUID pessoa_id,

    @NotBlank
    String nome,

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
    String cpf,

    @NotNull
    LocalDate dataNascimento,

    TipoSanguineo tipoSanguineo,

    Boolean alergia,

    Boolean tipoResponsavel,

    String cartaoNacional,

    String cartaoPlanoSaude

) {
    
}
