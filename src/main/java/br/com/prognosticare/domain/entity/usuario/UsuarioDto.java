package br.com.prognosticare.domain.entity.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDto(
    
    @NotBlank(message = "O campo nome não pode estar em branco")
    String email,

    @NotBlank
    @Size(min = 8, max = 16)
    String password
) {
    
}
