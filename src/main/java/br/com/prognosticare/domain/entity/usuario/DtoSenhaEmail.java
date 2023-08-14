package br.com.prognosticare.domain.entity.usuario;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoSenhaEmail(
    @NotNull
    UUID user_id,

    @NotBlank
    String email,

    @NotBlank
    String password
) {

    
    
}
