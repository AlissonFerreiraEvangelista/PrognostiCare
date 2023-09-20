package br.com.prognosticare.web.controller;

import jakarta.validation.constraints.NotBlank;

public record DtoTokenFCM (
    @NotBlank
    String tokenFCM
){

}
