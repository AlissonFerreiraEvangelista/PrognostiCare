package br.com.prognosticare.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import br.com.prognosticare.domain.entity.notificacao.Notificacao;
import br.com.prognosticare.domain.service.FirebaseMessagingService;


@RestController
@RequestMapping("notification")
@CrossOrigin("*")
public class NotificacaoController {

    @Autowired
    FirebaseMessagingService firebaseMessagingService;


    @PostMapping
    public String send(@RequestBody Notificacao notificacao){
        return firebaseMessagingService.sendNotificationByToken(notificacao);

    }
    
}
