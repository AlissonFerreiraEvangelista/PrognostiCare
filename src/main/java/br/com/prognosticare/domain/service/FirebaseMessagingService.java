package br.com.prognosticare.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import br.com.prognosticare.domain.entity.notificacao.Notificacao;

@Service
public class FirebaseMessagingService {
    
    @Autowired
    FirebaseMessaging firebaseMessaging;


    public String sendNotificationByToken(Notificacao notificacao){

        Notification notification = Notification
        .builder()
        .setTitle(notificacao.getTitle())
        .setBody(notificacao.getBody())
        .setImage(notificacao.getImg())
        .build();

        Message message = Message
        .builder()
        .setToken(notificacao.getRecipientToken())
        .setNotification(notification)
        .build();
        try {
            firebaseMessaging.send(message);
            return "Sucesso";
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "Erro";
        }

    }
}
