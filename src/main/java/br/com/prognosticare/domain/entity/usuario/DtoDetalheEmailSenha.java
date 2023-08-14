package br.com.prognosticare.domain.entity.usuario;

import java.util.UUID;



public record DtoDetalheEmailSenha (

    UUID user_id,
    String email,
    String password
){
    public DtoDetalheEmailSenha(Usuario usuario){
        this(usuario.getUser_id(), usuario.getEmail(), usuario.getPassword());

    }
}
