package br.com.prognosticare.domain.entity.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DtoAutenticacao(
    @NotBlank
    @Email
    String email,
    
    @NotBlank
    @Size(min = 3, max = 16)
    String password
) {
    
}
