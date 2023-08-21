package br.com.prognosticare.domain.entity.usuario;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DtoSenhaEmail(
    @NotNull
    UUID user_id,

    @NotBlank
    String email,

    @NotBlank
    @Size(min = 8, max = 16)
    String password
) {

    
    
}
