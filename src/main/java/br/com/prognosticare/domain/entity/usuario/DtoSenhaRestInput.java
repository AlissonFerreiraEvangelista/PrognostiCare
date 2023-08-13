package br.com.prognosticare.domain.entity.usuario;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;



public record DtoSenhaRestInput(

    @NotBlank
    @Email
    String email
    
    ){    
}
