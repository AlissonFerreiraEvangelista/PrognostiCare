package br.com.prognosticare.domain.entity.usuario;

import jakarta.validation.constraints.NotBlank;

public record DtoSenha(@NotBlank String password) {
    
}
