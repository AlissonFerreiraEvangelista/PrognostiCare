package br.com.prognosticare.domain.entity.notificacao;

import lombok.Data;

@Data
public class Notificacao {

    private String recipientToken;
    private String title;
    private String body;
    private String img;

    
}
