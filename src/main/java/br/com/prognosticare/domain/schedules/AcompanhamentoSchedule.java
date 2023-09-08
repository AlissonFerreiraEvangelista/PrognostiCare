package br.com.prognosticare.domain.schedules;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.*;

import br.com.prognosticare.domain.entity.acompanhamento.AcompanhamentoEntity;
import br.com.prognosticare.domain.enums.Status;
import br.com.prognosticare.domain.service.AcompanhamentoService;

@Component
public class AcompanhamentoSchedule {

    @Autowired
    AcompanhamentoService aService;

    String tokenFCM;

    @Scheduled(fixedRate = 60000)
    public void checkMedications() {
        LocalDateTime now = LocalDateTime.now();

        var acompanhamentos = aService.findAllByStatusEventoAberto();

        for (AcompanhamentoEntity acompanhamentoEntity : acompanhamentos) {
            if (now.isAfter(acompanhamentoEntity.getDataAcompanhamento())
                    && acompanhamentoEntity.getStatusEvento() == Status.ABERTO) {
                // sendNotification(acompanhamentoEntity);
                System.out.println(acompanhamentoEntity.getMedicacao());
                acompanhamentoEntity.atualizaProxaMedicacao();
                aService.save(acompanhamentoEntity);
            }
        }

    }

    private void sendNotification(AcompanhamentoEntity acompanhamento) {

        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle("Lembrete de Madicação")
                        .setBody("É hora de tomar " + acompanhamento.getMedicacao())
                        .setImage("tokenFCM")
                        .build())
                .setToken(tokenFCM)
                .build();

        try {
            FirebaseApp.initializeApp();
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }
    
}
