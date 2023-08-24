package br.com.prognosticare.domain.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DtoSenha(
    @NotBlank 
    @Size(min = 8, max = 16)
    String password) {
    
}
