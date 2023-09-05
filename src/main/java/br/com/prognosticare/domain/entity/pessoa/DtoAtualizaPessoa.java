package br.com.prognosticare.domain.entity.pessoa;


import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    @NotBlank
    @Pattern(regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}")
    String contato,

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    LocalDate dataNascimento,

    TipoSanguineo tipoSanguineo,

    Boolean alergia,

    String tipoAlergia,

    Boolean doador,

    String cartaoNacional,

    String cartaoPlanoSaude

) {
    
}
