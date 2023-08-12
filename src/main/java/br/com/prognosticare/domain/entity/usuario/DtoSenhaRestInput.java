package br.com.prognosticare.domain.entity.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record DtoSenhaRestInput(
    @Email 
    @NotBlank(message = "O campo nome não pode estar em branco ou não é um e-mail")
    String email){

    
}
