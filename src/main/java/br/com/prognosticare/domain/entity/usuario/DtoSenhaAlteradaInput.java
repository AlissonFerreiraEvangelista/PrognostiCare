package br.com.prognosticare.domain.entity.usuario;

import jakarta.validation.constraints.NotBlank;

public record DtoSenhaAlteradaInput(
    @NotBlank
    String password, 
    @NotBlank
    String tokenResetSenha) {
    
}
