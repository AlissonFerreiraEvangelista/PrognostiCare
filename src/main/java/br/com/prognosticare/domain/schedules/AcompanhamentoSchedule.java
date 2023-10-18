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
import br.com.prognosticare.infra.exception.ValidacaoException;

@Component
public class AcompanhamentoSchedule {

    @Autowired
    AcompanhamentoService aService;

    @Autowired
    FirebaseMessaging firebaseMessaging;


    @Scheduled(fixedRate = 60000)
    public void checkMedications() {
        LocalDateTime now = LocalDateTime.now();

        var acompanhamentos = aService.findAllByStatusEventoAberto();

        for (AcompanhamentoEntity acompanhamentoEntity : acompanhamentos) {
            if (now.isAfter(acompanhamentoEntity.getDataAcompanhamento())
                && acompanhamentoEntity.getStatusEvento() == Status.ABERTO 
                && acompanhamentoEntity.getIntervaloHora() != 0) {
                sendNotification(acompanhamentoEntity);
                System.out.println(acompanhamentoEntity.getMedicacao() + LocalDateTime.now());
                acompanhamentoEntity.atualizaProxaMedicacao();
                aService.save(acompanhamentoEntity);
            }
        }

    }

    

    private void sendNotification(AcompanhamentoEntity acompanhamento) {

        String tokenFCM = acompanhamento.getPessoa().getTokenFCM();
        if(tokenFCM != null && !tokenFCM.isEmpty()){
            
            Message message = Message.builder()
                    .setNotification(Notification.builder()
                            .setTitle("Lembrete de Madicação")
                            .setBody("É hora de tomar " + acompanhamento.getMedicacao())
                            .build())
                    .setToken(tokenFCM)
                    .build();
    
            try {
                firebaseMessaging.send(message);
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
            }
        }else{
            throw new ValidacaoException("Erro no AcompanhamentoSchedule");
        }
      
    }
    
}
