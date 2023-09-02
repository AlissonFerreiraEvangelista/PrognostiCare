package br.com.prognosticare.domain.entity.pessoa;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DtoCadastroPessoa(
    @NotBlank
    String nome,

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
    String cpf,

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    LocalDate dataNascimento,

    @NotBlank(message = "O campo nome não pode estar em branco")
    String email,

    @NotBlank
    @Size(min = 8, max = 16)
    String password

) {
    
}
