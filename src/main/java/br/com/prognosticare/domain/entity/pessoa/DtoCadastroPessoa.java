package br.com.prognosticare.domain.entity.pessoa;

import java.time.LocalDate;


import br.com.prognosticare.domain.entity.usuario.UsuarioDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DtoCadastroPessoa(
    @NotBlank
    String nome,

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
    String cpf,

    @NotNull
    LocalDate dataNascimento,

    @NotNull
    @Valid
    UsuarioDto usuario


) {
    
}
